using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore;
using Microsoft.eShopWeb.Infrastructure.Data;
using Microsoft.eShopWeb.Infrastructure.Identity;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace Microsoft.eShopWeb.Infrastructure;

public static class Dependencies
{
    public static void ConfigureServices(string environmentName, IConfiguration configuration, IServiceCollection services)
    {
        var dbServerConnection = new SqlConnectionStringBuilder
        {
            DataSource = "localhost",
            UserID = "sa",
            Password = "sup3rs3cr3t!"
        };

        var useOnlyInMemoryDatabase = false;
        if (configuration["UseOnlyInMemoryDatabase"] != null)
        {
            useOnlyInMemoryDatabase = bool.Parse(configuration["UseOnlyInMemoryDatabase"]);
        }

        if (useOnlyInMemoryDatabase)
        {
            services.AddDbContext<CatalogContext>(c =>
                c.UseInMemoryDatabase("Catalog"));

            services.AddDbContext<AppIdentityDbContext>(options =>
                options.UseInMemoryDatabase("Identity"));
        }
        else if (environmentName.ToLower() == "docker")
        {
            var eShopDb = new SqlConnectionStringBuilder(dbServerConnection.ConnectionString)
            {
                DataSource = "sqlserver",
                InitialCatalog = "eShopDB"
            };

            var identityDb = new SqlConnectionStringBuilder(eShopDb.ConnectionString)
            {
                InitialCatalog = "eShopIdentityDB"
            };

            services.AddDbContext<CatalogContext>(c =>
                c.UseSqlServer(eShopDb.ConnectionString));

            services.AddDbContext<AppIdentityDbContext>(options =>
                options.UseSqlServer(identityDb.ConnectionString));
        }
        else
        {
            // use real database
            // Requires LocalDB which can be installed with SQL Server Express 2016
            // https://www.microsoft.com/en-us/download/details.aspx?id=54284
            services.AddDbContext<CatalogContext>(c =>
                c.UseSqlServer(configuration.GetConnectionString("CatalogConnection")));
            // Add Identity DbContext
            services.AddDbContext<AppIdentityDbContext>(options =>
                options.UseSqlServer(configuration.GetConnectionString("IdentityConnection")));
        }
    }
}
