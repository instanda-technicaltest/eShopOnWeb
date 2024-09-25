import requests
import pytest

BASE_URL = "http://localhost:5200"


class TestAPI:

    def setup_method(self):
        """Setup method to log in and store the token before running tests."""
        self.token = self.login()

    def login(self):
        """Log in and return the token."""
        login_url = f"{BASE_URL}/api/authenticate"
        payload = {
            "username": "demouser@microsoft.com",
            "password": "Pass@word1"
        }
        response = requests.post(login_url, json=payload)
        assert response.status_code == 200, f"Expected 200 but got {response.status_code}"
        response_data = response.json()
        assert "token" in response_data, "Token was not returned"
        assert "result" in response_data, "Result was not returned"
        assert "isLockedOut" in response_data, "isLockedOut field missing"
        assert "isNotAllowed" in response_data, "isNotAllowed field missing"
        assert "requiresTwoFactor" in response_data, "requiresTwoFactor field missing"
        return response_data["token"]

    def test_login(self):
        """Test login via API."""
        self.token = self.login()

    def test_get_brands(self):
        """Test getting available brands."""
        headers = {"Authorization": f"Bearer {self.token}"}
        brands_url = f"{BASE_URL}/api/catalog-brands"
        response = requests.get(brands_url, headers=headers)
        assert response.status_code == 200, f"Expected 200 but got {response.status_code}"
        # Parse the response
        response_data = response.json()
        assert "catalogBrands" in response_data, "'catalogBrands' key not found in response"
        # Extract the brands
        brands = response_data["catalogBrands"]
        assert len(brands) > 0, "No brands returned"
        assert all("name" in brand for brand in brands), "Some brands don't have a 'name' field"

    def test_get_catalog_types(self):
        """Test getting catalog types."""
        headers = {"Authorization": f"Bearer {self.token}"}
        items_url = f"{BASE_URL}/api/catalog-types"
        response = requests.get(items_url, headers=headers)
        assert response.status_code == 200
        items = response.json()
        assert "catalogTypes" in items
        catalog_types = items["catalogTypes"]
        # Ensure the list is not empty and each item has a 'name'
        assert len(catalog_types) > 0, "No catalog types returned"
        assert all("name" in item for item in catalog_types), "Some catalog types don't have a 'name' field"

    def test_get_catalog_item_by_id(self):
        """Test getting a catalog item by ID."""
        headers = {"Authorization": f"Bearer {self.token}"}
        item_id = 3
        item_url = f"{BASE_URL}/api/catalog-items/{item_id}"
        # GET request to fetch item with ID 3
        response = requests.get(item_url, headers=headers)
        assert response.status_code == 200, f"Expected 200 but got {response.status_code}"
        item = response.json()
        assert "catalogItem" in item, "'id' not found in the response"

    def test_delete_catalog_item_by_id(self):
        """Test deleting a catalog item by ID."""
        headers = {"Authorization": f"Bearer {self.token}"}
        item_id = 12
        item_url = f"{BASE_URL}/api/catalog-items/{item_id}"
        response = requests.delete(item_url, headers=headers)
        # Check if the status code is 204 (No Content) which indicates successful deletion
        assert response.status_code == 204, f"Expected 204 but got {response.status_code}"
        get_response = requests.get(item_url, headers=headers)
        assert get_response.status_code == 404, f"Expected 404 but got {get_response.status_code} when retrieving deleted item"

    def test_update_catalog_item(self):
        """Test updating a catalog item."""
        headers = {"Authorization": f"Bearer {self.token}"}
        item_url = f"{BASE_URL}/api/catalog-items"
        payload = {
            "id": 3,  # ID of the catalog item being updated
            "name": ".NET Black & White Mug",  # Updated name of the catalog item
            "price": 9.99,  # Updated price
        }
        response = requests.put(item_url, json=payload, headers=headers)
        assert response.status_code == 200, f"Expected 200 but got {response.status_code}"

    def test_create_catalog_item(self):
        """Test creating a new catalog item."""
        headers = {"Authorization": f"Bearer {self.token}"}
        item_url = f"{BASE_URL}/api/catalog-items"
        # Payload with data for the new catalog item
        payload = {
            "name": "Azure sheet",
            "description": "This is a new catalog item.",
            "price": 19.99,
            "catalogBrandId": 1,
            "catalogTypeId": 2
        }
        response = requests.post(item_url, json=payload, headers=headers)
        assert response.status_code == 201, f"Expected 201 but got {response.status_code}"


