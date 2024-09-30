using QATaskAPI.Models;
using RestSharp;


namespace QATeskAPI.Models
{
    public class RestUtils
    {
        public static void InitialiseRestClient(string endpoint)
        {
            RestFactory.RestClient = new RestClient(endpoint);
            RestFactory.RestUtils = new RestUtils();
        }

        public static void InitialiseGetMethode()
        {
            RestFactory.RestRequest = new RestRequest(Method.GET);
        }
        public static void InitialisePostMethode()
        {
            RestFactory.RestRequest = new RestRequest(Method.POST);
        }
        public static void InitialisePutMethode()
        {
            RestFactory.RestRequest = new RestRequest(Method.PUT);
        }
        public static void InitialisePatchMethode()
        {
            RestFactory.RestRequest = new RestRequest(Method.PATCH);
        }
        public static void InitialiseDeleteMethode()
        {
            RestFactory.RestRequest = new RestRequest(Method.DELETE);
        }
        public static void AddJasonBody(string body)
        {
            RestFactory.RestRequest.AddJsonBody(body);
        }

    }
}
