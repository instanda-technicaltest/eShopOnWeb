using QATeskAPI.Models;
using RestSharp;

namespace QATaskAPI.Models
{
    public abstract class RestFactory
    {

        public static RestClient RestClient { get; set; }

        public static RestRequest RestRequest { get; set; }

        public static IRestResponse RestResponse { get; set; }

        public static RestUtils RestUtils { get; set; }

    }
}
