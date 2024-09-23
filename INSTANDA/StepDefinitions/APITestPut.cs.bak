using System.Text;
using Newtonsoft.Json;
using INSTANDA.Configuration;


namespace INSTANDA.StepDefinitions
{
    [Binding]
    public class APITestPut
    {

        private string _apiEndpoint;
        private HttpResponseMessage _response;
        private Dictionary<string, object> _catalogItem;

        private readonly HttpClient _httpClient;

        public APITestPut()
        {
            _httpClient = new HttpClient();
        }

        [Given(@"I have the updated catalog item data with id (.*)")]
        public void GivenIHaveTheUpdatedCatalogItemDataWithId(string endpoint)
        {
            _apiEndpoint = $"{SetConfig.ApiUrl}{endpoint}";
        }

        [Given(@"I have the updated catalog item data with id (.*)")]
        public void GivenIHaveTheUpdatedCatalogItemDataWithId(int id, Table table)
        {
            _catalogItem = new Dictionary<string, object>
            {
                {"id", id},
                {"name", table.Rows[0]["name"]},
                {"description", table.Rows[0]["description"]},
                {"price", decimal.Parse(table.Rows[0]["price"])},
                {"pictureUri", table.Rows[0]["pictureUri"]},
                {"catalogTypeId", int.Parse(table.Rows[0]["catalogTypeId"])},
                {"catalogBrandId", int.Parse(table.Rows[0]["catalogBrandId"])}
            };
        }

        [When(@"I send a PUT request to the API with this data")]
        public async Task WhenISendAPUTRequestToTheAPIWithThisData()
        {
            var json = JsonConvert.SerializeObject(_catalogItem);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            _response = await _httpClient.PutAsync(_apiEndpoint, content);
        }

        //[Then(@"the response status code should be (.*)")]
        //public void ThenTheResponseStatusCodeShouldBe(int statusCode)
        //{
        //    Assert.AreEqual(statusCode, (int)_response.StatusCode);
        //}

        //[Then(@"the response should contain ""(.*)""")]
        //public async Task ThenTheResponseShouldContain(string expectedField)
        //{
        //    var content = await _response.Content.ReadAsStringAsync();
        //    Assert.IsTrue(content.Contains(expectedField));
        //}
    }
}
