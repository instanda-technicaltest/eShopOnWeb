using Microsoft.EntityFrameworkCore;
using Microsoft.eShopWeb.Infrastructure.Data;

namespace InfrastructureTests;

public class TestDatabaseFixture : IDisposable
{
    private string databaseName = Guid.NewGuid().ToString();
    public CatalogContext Context { get; private set; }

    public TestDatabaseFixture()
    {
        var options = new DbContextOptionsBuilder<CatalogContext>()
            .UseInMemoryDatabase(databaseName)
            .Options;

        Context = new CatalogContext(options);
        Context.Database.EnsureDeleted();
    }

    public void Dispose()
    {
        Context.Dispose();
    }
}
