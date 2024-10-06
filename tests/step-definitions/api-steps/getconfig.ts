import { AxiosRequestConfig } from 'axios';

// Function to get Axios configuration with Authorization, accept, and content-type headers
export function getConfig(token: string): AxiosRequestConfig<any> {
  const config: AxiosRequestConfig<any> = {
    headers: {
      'accept': 'application/json', // Accept JSON responses
      'Authorization': `Bearer ${token}`, // Bearer token for Authorization
      'Content-Type': 'application/json' // Content-Type to send JSON data
    },
    timeout: 6000 // Set timeout of 6 seconds
  };

  return config;
}
