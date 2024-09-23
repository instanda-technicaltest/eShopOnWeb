using Newtonsoft.Json;
using INSTANDA.Configuration;
using System.Text;
using System.Net;
using NUnit.Framework;

namespace INSTANDA.StepDefinitions
{
    [Binding]
    public class APITestPost
    {

        private string _apiEndpoint;
        private HttpResponseMessage _response;
        private readonly HttpClient _httpClient;
        private List<Dictionary<string, object>> _catalogItems;

        public APITestPost()
        {
            _httpClient = new HttpClient();
        }        

        [Given(@"I have the POST API endpoint ""([^""]*)""")]
        public void GivenIHaveThePOSTAPIEndpoint(string endpoint)
        {
            //_apiEndpoint = $"{SetConfig.ApiUrl}{endpoint}";
            _apiEndpoint = endpoint;
        }


        [Given(@"I have the following catalog items data")]
        public void GivenIHaveTheFollowingCatalogItemsData(Table table)
        {
            _catalogItems = new List<Dictionary<string, object>>();

            foreach (var row in table.Rows)
            {
                var catalogItem = new Dictionary<string, object>
                {
                    {"id", int.Parse(row["id"])},
                    {"name", row["name"]},
                    {"description", row["description"]},
                    {"price", decimal.Parse(row["price"])},
                    {"pictureUri", row["pictureUri"]},
                    {"catalogTypeId", int.Parse(row["catalogTypeId"])},
                    {"catalogBrandId", int.Parse(row["catalogBrandId"])}
                };

                _catalogItems.Add(catalogItem);
            }
        }

        [When(@"I send a POST request to the API")]
        public async Task WhenISendAPOSTRequestToTheAPI()
        {
            var requestBody = new
            {
                catalogItems = _catalogItems,
                pageCount = 1
            };

            var json = JsonConvert.SerializeObject(requestBody);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            _response = await _httpClient.PostAsync(_apiEndpoint, content);
        }

        [Then(@"the POST response status code should be (.*)")]
        public void ThenTheResponseStatusCodeShouldBe(int statusCode)
        {
            Assert.AreEqual(statusCode, (int)_response.StatusCode);
        }

        //[Then(@"the response should contain ""(.*)""")]
        //public async Task ThenTheResponseShouldContain(string expectedField)
        //{
        //    var content = await _response.Content.ReadAsStringAsync();
        //    Assert.IsTrue(content.Contains(expectedField));
        //}
    }
}
