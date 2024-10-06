import { axios } from "../../setup/world"
import { AxiosRequestConfig } from 'axios';
import { getConfig } from "./getconfig";

let response: any;

export class PostCatalogItem {
    async postRequest(endpointUrl: string, token: string) {

        const postData = {
            catalogBrandId: 1,
            catalogTypeId: 2,
            description: "Test Item Description",
            name: `Test Item ${Date.now()}`,
            pictureUri: "http://example.com/pic.jpg",
            price: Math.floor(Math.random() * 500) + 1
        };

        const config: AxiosRequestConfig = getConfig(token);
        response = await axios.post(process.env.API_BASE_URL + endpointUrl, postData, config);
        return response;
    }

    async postBadRequest400(endpointUrl: string, token: string) {

        const invalidData = {
            catalogBrandId: "",
            catalogTypeId: "",
            description: "Test Item Description",
            name: `Test Item ${Date.now()}`,
            pictureUri: "http://example.com/pic.jpg",
            price: Math.floor(Math.random() * 500) + 1
        };

        const config: AxiosRequestConfig = getConfig(token);

        // Add validateStatus to consider 400 as a valid response for this test
        config.validateStatus = function (status) {
            // Accept any status code less than 500, i.e., 400 is treated as a valid response
            return status < 500;
        };

        response = await axios.post(process.env.API_BASE_URL + endpointUrl, invalidData, config);
        return response;
    }
    async postBadRequest500(endpointUrl: string, token: string) {

        const invalidData = {
            catalogBrandId: "450",
            catalogTypeId: "450",
            description: "Test Item Description",
            name: `Test Item ${Date.now()}`,
            pictureUri: "http://example.com/pic.jpg",
            price: Math.floor(Math.random() * 500) + 1
        };

        const config: AxiosRequestConfig = getConfig(token);

        // Add validateStatus to consider 400 as a valid response for this test
        config.validateStatus = function (status) {
            // Accept any status code less than 500, i.e., 500 is treated as a valid response
            return status <= 500;
        };

        response = await axios.post(process.env.API_BASE_URL + endpointUrl, invalidData, config);
        return response;
    }
}