import axios, { AxiosRequestConfig } from 'axios';
import { getConfig } from './getconfig'; // Assuming getConfig creates the Axios configuration

export class GetCatalogItem {
    async getRequest(endpointUrl: string, token: string, catalogBrandId: string, catalogTypeId: string) {

        // Get the config using the token (contains headers and other settings)
        const config: AxiosRequestConfig = getConfig(token);

        // Add the query parameters (catalogBrandId, catalogTypeId) to the config
        config.params = {
            catalogBrandId: catalogBrandId,
            catalogTypeId: catalogTypeId,
        };

        // Send the GET request using Axios, passing the full URL and config with headers + params
        const response = await axios.get(`${process.env.API_BASE_URL}${endpointUrl}`, config);

        return response;
    }
}
