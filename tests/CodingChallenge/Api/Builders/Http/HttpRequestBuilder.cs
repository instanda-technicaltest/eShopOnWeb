using System;
using System.Collections.Generic;
using System.Collections.Concurrent;
using System.Linq;
using System.Text;
using System.Net.Http;
using System.Threading.Tasks;
using System.Net.Http.Json;
using System.IO;

namespace CodingChallenge.Api.Builders.Http
{
    public class HttpRequestBuilder
    {
        private HttpMethod? _method;
        private string? path;
        private string? baseUrl;
        private HttpContent? content;
        public HttpRequestBuilder AddMethod(HttpMethod method)
        {
            this._method = method;
            return this;
        }

        public HttpRequestBuilder AddRequestUri(string baseUrl, string requestUri)
        {
            this.baseUrl = baseUrl;
            path = requestUri;

            return this;
        }

        /// <summary>
        /// Sets body content for the request.
        /// </summary>
        /// <param name="content">The body content.</param>
        /// <returns>This portion of the overall request.</returns>
        public HttpRequestBuilder AddContent(HttpContent content)
        {
            this.content = content;
            return this;
        }

        public async Task<HttpResponseMessage> SendAsync()
        {
            //Create the request message based on the request in the builder
            var request = new HttpRequestMessage
            {
                Method = _method!,
                RequestUri = new Uri($"{baseUrl}{path}")
            };

            //Creates or Gets an existing HttpClient for the BaseUrl being used
            var httpClient = HttpClientFactory.GetHttpClientInstance(baseUrl!);

            return await httpClient.SendAsync(request);
        }

        public async Task<HttpResponseMessage> SendPostAsync()
        {
            //Create the request message based on the request in the builder
            var request = new HttpRequestMessage
            {
                Method = _method!,
                RequestUri = new Uri($"{baseUrl}{path}"),
                Content = content
            };

            //Creates or Gets an existing HttpClient for the BaseUrl being used
            var httpClient = HttpClientFactory.GetHttpClientInstance(baseUrl!);

            return await httpClient.SendAsync(request);
        }
    }

    //This static factory ensures that we are using one HttpClient per BaseUrl used in the solution.
    //This prevents a large number sockets being left open after the tests are run
    public static class HttpClientFactory
    {
        private static ConcurrentDictionary<string, HttpClient> httpClientList = new ConcurrentDictionary<string, HttpClient>();

        public static HttpClient GetHttpClientInstance(string baseUrl)
        {
            // to ByPass the certificate 'UntrustedRoot'
            HttpClientHandler clientHandler = new HttpClientHandler();
            clientHandler.ServerCertificateCustomValidationCallback = (sender, cert, chain, sslPolicyErrors) => { return true; };

            if (!httpClientList.ContainsKey(baseUrl))
                httpClientList.TryAdd(baseUrl, new HttpClient(clientHandler));

            return httpClientList[baseUrl];
        }
    }
}

