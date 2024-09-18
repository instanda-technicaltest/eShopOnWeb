using Newtonsoft.Json;

namespace CodingChallenge.Models;

public class LoginApiModel
{
    [JsonProperty("username")] 
    public string? Username { get; set; }

    [JsonProperty("password")]
    public string? Password { get; set; }
}
