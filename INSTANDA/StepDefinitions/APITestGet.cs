using NUnit.Framework;
using System.Net;
using System.Net.Http;
using TechTalk.SpecFlow;
using INSTANDA.Configuration;

namespace INSTANDA.StepDefinitions
{
    [Binding]
    public class APITestGET
    {

        private string _apiEndpoint;
        private HttpResponseMessage _response;

        private readonly HttpClient _httpClient;

        public APITestGET()
        {
            _httpClient = new HttpClient();
        }

        [Given(@"I have the API endpoint ""([^""]*)""")]
        public void GivenIHaveTheAPIEndpoint(string endpoint)
        {   
            _apiEndpoint = $"{SetConfig.ApiUrl}{endpoint}";
        }

        [When(@"I send a GET request to the endpoint")]
        public async Task WhenISendAGETRequestToTheEndpoint()
        {
            _response = await _httpClient.GetAsync(_apiEndpoint);
        }

        [Then(@"the response status code should be (.*)")]
        public void ThenTheResponseStatusCodeShouldBe(int statusCode)
        {
            Assert.AreEqual(statusCode, (int)_response.StatusCode);
        }

        [Then(@"the response should contain ""([^""]*)""")]
        public async Task ThenTheResponseShouldContain(string expectedField)
        {
            var content = await _response.Content.ReadAsStringAsync();
            Assert.IsTrue(content.Contains(expectedField));
        }
    }
}
