using System.Dynamic;
using System.Net.Http.Json;
using Newtonsoft.Json;

namespace PublicApiTests;
public class HttpServiceFixture : IAsyncLifetime
{
    private readonly HttpClient httpClient = new() { BaseAddress = new Uri(Environment.GetEnvironmentVariable("HostTestApiBaseUrl")?.ToString()) };
    public HttpClient HttpClient => httpClient;
    private string? adminToken;
    private string? userToken;
    public string AdminToken => adminToken ?? throw new NullReferenceException(nameof(adminToken));
    public string UserToken => userToken ?? throw new NullReferenceException(nameof(userToken));

    public static JsonContent GetTestAuthRequestContent(string? altUsername = null, string? altPassword = null)
    {
        var username = altUsername ?? Environment.GetEnvironmentVariable("HostTestUsername");
        var password = altPassword ?? Environment.GetEnvironmentVariable("HostTestPassword");

        return JsonContent.Create(new { username, password });
    }

    public async Task InitializeAsync()
    { 
        adminToken = await GetTestAuthTokenAsync(true);
        userToken = await GetTestAuthTokenAsync();
    }

    public Task DisposeAsync() => Task.CompletedTask;

    private static JsonContent GetTestAdminAuthRequestContent()
    {
        return GetTestAuthRequestContent(
            Environment.GetEnvironmentVariable("HostTestAdminUsername"),
            Environment.GetEnvironmentVariable("HostTestAdminPassword"));
    }

    public async Task<string?> GetTestAuthTokenAsync(bool isAdmin = false)
    {
        var content = isAdmin 
            ? GetTestAdminAuthRequestContent() 
            : GetTestAuthRequestContent();

        var response = await HttpClient.PostAsync("authenticate", content);

        if (!response.IsSuccessStatusCode)
            return null;

        var stringContent = await response.Content.ReadAsStringAsync();
        var contentDict = JsonConvert.DeserializeObject<ExpandoObject>(stringContent) as IDictionary<string, object>;

        return contentDict?["token"]?.ToString();      
    }
}
