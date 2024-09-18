using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Playwright;
namespace CodingChallenge.Configurations
{
    public class PlaywrightSettings
    {
        public string? Browser { get; set; }
        public string? BaseUrl { get; set; }
        public string? ApiUrl { get; set; }
        public  BrowserNewContextOptions? BrowserContextOptions { get; set; }
        public BrowserTypeLaunchOptions? BrowserTypeLaunchOptions { get; set; }

    }
}
