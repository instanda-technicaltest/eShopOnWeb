using FluentAssertions;
using Microsoft.eShopWeb.ApplicationCore.Entities.BasketAggregate;
using Microsoft.eShopWeb.Infrastructure.Data.Queries;

namespace InfrastructureTests;

public class BasketQueryServiceTests : IClassFixture<TestDatabaseFixture>
{
    private TestDatabaseFixture testDatabaseFixture;

    public BasketQueryServiceTests(TestDatabaseFixture testDatabaseFixture)
    {
        this.testDatabaseFixture = testDatabaseFixture;
    }

    [Fact]
    public async Task BasketQueryServiceTests_Ok()
    {
        // Arrange
        string buyerId = Guid.NewGuid().ToString();

        var testBasket = new Basket(buyerId);
        testBasket.AddItem(10, 100.0m, 1);
        testBasket.AddItem(20, 200.0m, 2);

        const int expectedQuantity = 3;

        testDatabaseFixture.Context.Baskets.Add(testBasket);
        testDatabaseFixture.Context.SaveChanges();

        // Act
        var basketQueryService = new BasketQueryService(testDatabaseFixture.Context);
        var count = await basketQueryService.CountTotalBasketItems(buyerId);

        // Assert
        count.Should().Be(expectedQuantity);
    }

    [Fact]
    public async Task BasketQueryServiceTests_IgnoresBobOk()
    {
        // Arrange
        const int expectedQuantity = 3;
        string testBuyerId = Guid.NewGuid().ToString();

        var testBasket = new Basket(testBuyerId);
        testBasket.AddItem(1, 2.0m, expectedQuantity);
        testDatabaseFixture.Context.Baskets.Add(testBasket);

        // Add a second buyer we expect to be ignored
        var expectedToBeIgnoredBasket = new Basket("bob");
        expectedToBeIgnoredBasket.AddItem(2, 4.0m, 101);
        testDatabaseFixture.Context.Baskets.Add(expectedToBeIgnoredBasket);

        testDatabaseFixture.Context.SaveChanges();

        // Act
        var basketQueryService = new BasketQueryService(testDatabaseFixture.Context);
        var count = await basketQueryService.CountTotalBasketItems(testBuyerId);

        // Assert
        count.Should().Be(expectedQuantity);
    }
}
