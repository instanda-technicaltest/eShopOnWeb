using Microsoft.eShopWeb.ApplicationCore.Interfaces;

namespace ApplicationCoreTests;
public class StubLogger<T> : IAppLogger<T>
{
    public void LogInformation(string message, params object[] args) { }
    public void LogWarning(string message, params object[] args) { }
}
