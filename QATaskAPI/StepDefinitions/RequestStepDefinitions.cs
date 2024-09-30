using System.Net;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using QATaskAPI.Models;
using QATeskAPI.Models;
using RestSharp;

namespace QATaskAPI.StepDefinitions
{
    [Binding]
    public class RequestStepDefinitions
    {
        private string token;
        private int tokenKey;
        private readonly string headerName;
        private readonly object headerValue;
        private readonly ScenarioContext scenarioContext;
        private string lastResponseBody;

        private string GetLastResponseBody()
        {
            return lastResponseBody;
        }

        private Dictionary<string, string> variableStore = new Dictionary<string, string>();

        private void SetVariable(string variableName, string value)
        {

            if (variableStore.ContainsKey(variableName))
            {
                variableStore[variableName] = value;
            }
            else
            {
                variableStore.Add(variableName, value);
            }
        }

        [Given(@"I authenticate with valid credentials")]
        public void GivenIAuthenticateWithValidCredentials()
        {
            RestUtils.InitialiseRestClient("http://localhost:5200/api/authenticate");
            RestUtils.InitialisePostMethode();

            var authBody = new
            {
                username = "admin@microsoft.com",
                password = "Pass@word1"
            };

            string jsonBody = JsonConvert.SerializeObject(authBody);
            RestUtils.AddJasonBody(jsonBody);

            RestFactory.RestResponse = RestFactory.RestClient.Execute(RestFactory.RestRequest);
            var authResponseContent = RestFactory.RestResponse.Content;

            JObject jsonResponse = JObject.Parse(authResponseContent);
            if (jsonResponse.ContainsKey("token"))
            {
                token = jsonResponse["token"]?.ToString();
                Console.WriteLine("Token stored: " + token);
            }
            else
            {
                throw new Exception("Authentication failed. Token not found.");
            }
        }

        [Given(@"I construct ""([^""]*)"" for ""([^""]*)""")]
        public void GivenIConstructFor(string httpMethod, string endpoint)
        {
            if (endpoint.Contains("{externalId}"))
            {
                var externalId = ScenarioContext.Current["externalId"].ToString();
                endpoint = endpoint.Replace("{externalId}", externalId);
            }
            RestUtils.InitialiseRestClient("http://localhost:5200" + endpoint);
            switch (httpMethod)
            {
                case "GET":
                    RestUtils.InitialiseGetMethode();
                    break;

                case "POST":
                    RestUtils.InitialisePostMethode();
                    break;

                case "PUT":
                    RestUtils.InitialisePutMethode();
                    break;

                case "PATCH":
                    RestUtils.InitialisePatchMethode();
                    break;

                case "DELETE":
                    RestUtils.InitialiseDeleteMethode();
                    break;
                default:
                    break;
            }
        }

        [Then(@"I store the value of ""([^""]*)"" from the response body")]
        public void ThenIStoreTheValueOfFromTheResponseBody(string tokenkey)
        {
            var client = new RestClient("http://localhost:5200");

            var authRequest = new RestRequest("/api/authenticate", Method.POST);
            authRequest.AddHeader("Content-Type", "application/json");

            var authBody = new
            {
                username = "admin@microsoft.com",
                password = "Pass@word1"
            };
            authRequest.AddJsonBody(authBody);

            var authResponse = client.Execute(authRequest);

            if (authResponse.IsSuccessful)
            {
                dynamic jsonResponse = Newtonsoft.Json.JsonConvert.DeserializeObject(authResponse.Content);
                token = jsonResponse[tokenKey];
                Console.WriteLine("Token stored: " + token);
            }
            else
            {
                throw new Exception("Failed to retrieve token. Status: " + authResponse.StatusCode);
            }
        }

        [When(@"I set header ""([^""]*)"" to ""([^""]*)""")]
        public void WhenISetHeaderTo(string headerName, string headerValue)
        {
            if (RestFactory.RestRequest == null)
            {
                throw new InvalidOperationException("RestRequest has not been initialized. Please ensure that the request is set up correctly.");
            }

            if (headerName.Equals("Authorization", StringComparison.OrdinalIgnoreCase))
            {
                if (string.IsNullOrEmpty(token))
                {
                    throw new InvalidOperationException("Token is null or empty. Please authenticate first.");
                }
                headerValue = $"Bearer {token}";
            }

            RestFactory.RestRequest.AddHeader(headerName, headerValue);
        }


        [When(@"I submit a request")]
        public void WhenISubmitARequest()
        {
            try
            {
                var client = RestFactory.RestClient;
                var request = RestFactory.RestRequest;
                var response = client.Execute(request);
                RestFactory.RestResponse = response;

                if (response.StatusCode == 0 || response.StatusCode >= HttpStatusCode.BadRequest)
                {
                    throw new Exception($"Request failed with status code: {response.StatusCode} and message: {response.ErrorMessage}");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Request failed with exception: {ex.Message}");
                throw;
            }
        }

        [When(@"I extract the ""([^""]*)"" from response into ""([^""]*)""")]
        public void WhenIExtractTheFromResponseInto(string key, string variableName)
        {

            var response = JObject.Parse(RestFactory.RestResponse.Content);


            var catalogItem = response["catalogItem"];

            if (catalogItem == null)
            {
                throw new FormatException("The 'catalogItem' object was not found in the response.");
            }

            var extractedValue = catalogItem[key]?.ToString();

            if (string.IsNullOrEmpty(extractedValue))
            {
                throw new FormatException($"The key '{key}' was not found in the 'catalogItem' object.");
            }


            ScenarioContext.Current[variableName] = extractedValue;
        }

        [Then(@"I expect response content to be ""(.*)""catalogTypes""(.*)""id""(.*)""name""(.*)""USB Memory Stick""(.*)""id""(.*)""name""(.*)""Sheet""(.*)""id""(.*)""name""(.*)""T-Shirt""(.*)""id""(.*)""name""(.*)""Mug""(.*)""")]
        public void ThenIExpectResponseContentToBeCatalogTypesidnameUSBMemoryStickidnameSheetidnameT_ShirtidnameMug(string content)
        {
            var reponseContent = RestFactory.RestResponse.Content;
            reponseContent.Should().Be(content);
        }

        [Then(@"I extract ""(.*)"" value into ""(.*)"" from response")]
        public void ThenIExtractValueIntoFromResponse(string jsonKey, string variableName)
        {
            var response = RestFactory.RestResponse;

            Console.WriteLine("Response Body: " + response.Content);

            if (string.IsNullOrEmpty(response.Content))
            {
                throw new Exception("Response body is null or empty.");
            }

            try
            {

                var jsonResponse = JObject.Parse(response.Content);
                Console.WriteLine("Parsed Response: " + jsonResponse.ToString());
                var token = jsonResponse.SelectToken($"catalogItem.{jsonKey}");

                if (token == null)
                {
                    throw new Exception($"Key 'catalogItem.{jsonKey}' not found in the response.");
                }

                scenarioContext[variableName] = token.ToString();
            }
            catch (JsonException ex)
            {
                throw new Exception("Failed to parse response content as JSON.", ex);
            }
            catch (NullReferenceException ex)
            {
                throw new Exception("A null reference occurred during token extraction.", ex);
            }
        }

        [Then(@"I expect statuscode to be ""([^""]*)""")]
        public void ThenIExpectStatuscodeToBe(HttpStatusCode ResponseStatusCode)
        {
            var status = RestFactory.RestResponse.StatusCode;
            status.Should().Be(ResponseStatusCode);
        }

        [Then(@"I expect response content to contain ""(.*)""")]
        public void ThenIExpectResponseContentToContain(string Content)
        {
            var responseContent = RestFactory.RestResponse.Content;
            responseContent.Should().Contain(Content);
        }

        [When(@"I pass body ""(.*)""")]
        public void WhenIPassBody(string body)
        {
            RestUtils.AddJasonBody(body);
        }

        [Then(@"I expect response content to be ""(.*)""")]
        public void ThenIExpectResponseContentToBe(string content)
        {
            var reponseContent = RestFactory.RestResponse.Content;
            reponseContent.Should().Be(content);
        }

    }
}


