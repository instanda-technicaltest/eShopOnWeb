import { axios } from "../../setup/world"
import { AxiosRequestConfig } from 'axios';
import { getConfig } from './getconfig';

export class UpdateCatalogItem {
    async updateRequest(endpoint: string, token: string) {

        const updateData = {
            "id": 37,
            "catalogBrandId": 1,
            "catalogTypeId": 2,
            description: `Test-${Math.random().toString(36).substring(2, 7)}-temp`,  // Generates a random description string
            name: `Product-${Math.random().toString(36).substring(2, 7)}`,  // Generates a random product name
            "pictureBase64": "string",
            "pictureUri": "string",
            "pictureName": "string",
            "price": 10
        }

        const config: AxiosRequestConfig = getConfig(token);

        const response = await axios.put(process.env.API_BASE_URL + endpoint, updateData, config);
        return response;
    }
}
