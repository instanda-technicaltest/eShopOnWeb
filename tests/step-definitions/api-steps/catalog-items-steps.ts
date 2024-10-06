import { Given, When, Then } from '@cucumber/cucumber';
import { authToken } from '../../setup/world'; // Import the token
import { PostCatalogItem } from './post-catalog';
import { APIAssertions } from '../Assertions/api-assertions';
import { commonContext } from '../../setup/global';
import { UpdateCatalogItem } from './update-catalog-item';
import { GetCatalogItem } from './get-catalog-item';

let createdItemId: string;
let response: any;
let apiAssertions: APIAssertions;

//POST request scenario
Given('I send a POST request to {string} with valid data', async function (endpointUrl: string) {
  let postcatalogitem = new PostCatalogItem();
  response = await postcatalogitem.postRequest(endpointUrl, authToken);
});

When('the status code should be {string}', async function (statusCode: string) {
  createdItemId = response.data.catalogItem.id; // Store the created item ID
  apiAssertions = new APIAssertions();
  let intStatusCode: number = parseInt(statusCode, 10);
  apiAssertions.assertStatusCode(response, intStatusCode);
  commonContext.logger.info("Verified status code successfully: " + intStatusCode);
});

Then('the response should contain the newly created item', async function () {
  apiAssertions.assertExists(createdItemId);// Validate that the item ID exists
  commonContext.logger.info("Added new item: " + createdItemId);
});

//PUT request scenario
// Step for sending a PUT request to update the created catalog item
Given('I send a PUT request to {string} with updated data', async function (endpointUrl: string) {
  let updatecatalogitem = new UpdateCatalogItem();
  response = await updatecatalogitem.updateRequest(endpointUrl, authToken); // Pass the createdItemId for updating
});

// Step for asserting the updated response contains the updated item details
Then('the response should contain the updated item details', async function () {
  const updatedItemName = response.data.catalogItem.name; // Assuming response contains the updated item name
  apiAssertions.assertExists(updatedItemName); // Validate that the updated item name exists
  commonContext.logger.info("Updated item name exists: " + updatedItemName);
});

// GET request scenario
// Step for sending a GET request to retrieve the created catalog item
Given('I send a GET request to {string} with catalogBrandId {string} and catalogTypeId {string}', async function (endpointUrl: string, catalogBrandId: string, catalogTypeId: string) {
  let getcatalogitem = new GetCatalogItem();
  response = await getcatalogitem.getRequest(endpointUrl, authToken, catalogBrandId, catalogTypeId); // Send GET request with query params
});

Then('the status code returned should be {string}', async function (statusCode: string) {
  apiAssertions = new APIAssertions();
  let intStatusCode: number = parseInt(statusCode, 10);
  apiAssertions.assertStatusCode(response, intStatusCode);
  commonContext.logger.info("Verified status code successfully: " + intStatusCode);
});

// Step for asserting the retrieved catalog item details
Then('the response should contain catalog items and should not be empty', async function () {
  const catalogItems = response.data.catalogItems;

  // Assert that the catalogItems array exists and is not empty
  await apiAssertions.assertExists(catalogItems); // Validate that the catalogItems array exists

  // Assert that the catalogItems array is not empty
  await apiAssertions.assertArrayNotEmpty(catalogItems, 'Catalog Items');
  commonContext.logger.info(`Catalog items returned: ${catalogItems.length} items found.`);
});

Given('I send a POST request to {string} with missing data', async function (endpointUrl: string) {
  let postcatalogitem = new PostCatalogItem();
  response = await postcatalogitem.postBadRequest400(endpointUrl, authToken);
});

Given('I send a POST request to {string} with invalid data', async function (endpointUrl: string) {
  let postcatalogitem = new PostCatalogItem();
  response = await postcatalogitem.postBadRequest500(endpointUrl, authToken);
});