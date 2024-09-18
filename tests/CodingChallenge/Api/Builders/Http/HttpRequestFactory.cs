using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Http;
using System.Threading.Tasks;
using Newtonsoft.Json;
using System.Net.Http.Headers;

using System.Net.Http.Json;

namespace CodingChallenge.Api.Builders.Http
{
    public class HttpRequestFactory
    {
        public static async Task<HttpResponseMessage> Delete(
          string apiBaseUrl,
          string path)
        {
            return await new HttpRequestBuilder()
                                .AddMethod(HttpMethod.Delete)
                                .AddRequestUri(apiBaseUrl, path)
                                .SendAsync();
        }
        /// <summary>
        /// The POST request builder - takes in request details to construct and execute the POST request.
        /// </summary>
        /// <param name="payload">The details of the POST request.</param>
        /// <returns>The response to the POST request.</returns>
        public static async Task<HttpResponseMessage> PostAsync(string apiBaseUrl,
          string path, HttpContent payload)
        {
            return await new HttpRequestBuilder()
                                .AddMethod(HttpMethod.Post)
                                .AddRequestUri(apiBaseUrl, path)
                                .AddContent(payload)
                                .SendPostAsync();
        }
        /// <summary>
        /// Creates HttpContent from the object provided.
        /// </summary>
        /// <typeparam name="TContent">Generic type parameter.</typeparam>
        /// <param name="content">A potentially empty object representing content.</param>
        /// <returns>HttpContent to use in requests.</returns>
        public static HttpContent CreateHttpContent<TContent>(TContent content)
        {
            // If the content is empty then create empty HttpContent.
            if (EqualityComparer<TContent>.Default.Equals(content, default))
            {
                return new ByteArrayContent(Array.Empty<byte>());
            }

            // if the content is not empty, then create HttpContent with the Accept header set to 'application/json'.
            var json = JsonConvert.SerializeObject(content);
            var result = new ByteArrayContent(Encoding.UTF8.GetBytes(json));
            result.Headers.ContentType = new MediaTypeHeaderValue("application/json");
            return result;
        }
    }
}
