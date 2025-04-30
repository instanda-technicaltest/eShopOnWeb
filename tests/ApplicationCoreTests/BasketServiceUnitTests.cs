using FluentAssertions;
using Microsoft.eShopWeb.ApplicationCore.Entities.BasketAggregate;
using Microsoft.eShopWeb.ApplicationCore.Interfaces;
using Microsoft.eShopWeb.ApplicationCore.Services;
using Microsoft.eShopWeb.ApplicationCore.Specifications;
using Moq;

namespace ApplicationCoreTests;

public class BasketServiceUnitTests
{
    private CancellationToken ItIsAny_CT = It.IsAny<CancellationToken>();

    private const string username = "bob";
    private const int catalogItemId = 1;
    private const decimal price = 10.0m;
    private const int quantity = 2;

    [Fact]
    public async Task BasketService_AddItemToBasket_Ok()
    {
        // Arrange
        var expectedBasket = new Basket(username);
        expectedBasket.AddItem(catalogItemId, price, quantity); 

        var mockBasketRepository = new Mock<IRepository<Basket>>();

        // Stub
        mockBasketRepository.Setup(x => x.GetBySpecAsync(It.IsAny<BasketWithItemsSpecification>(), ItIsAny_CT))
            .ReturnsAsync((Basket)null);

        // Verifiable mock
        mockBasketRepository.Setup(x => 
            x.AddAsync(It.Is<Basket>(b => b.BuyerId == username), ItIsAny_CT))
                .ReturnsAsync(new Basket(username))
                    .Verifiable();

        // Verifiable mock
        mockBasketRepository.Setup(x =>
            x.UpdateAsync(It.Is<Basket>(b => b.BuyerId == username), ItIsAny_CT))
                .Returns(Task.CompletedTask)
                    .Verifiable();

        var basketService = new BasketService(mockBasketRepository.Object, new StubLogger<BasketService>());

        // Act
        var actual = await basketService.AddItemToBasket(username, catalogItemId, price, quantity);

        // Assert 
        mockBasketRepository.Verify();
        actual.Should().BeEquivalentTo(expectedBasket);
    }

    [Fact]
    public async Task BasketService_DeleteFromBasket_Ok()
    {
        // Arrange
        var deleteFromBasket = new Basket(username);
        deleteFromBasket.AddItem(catalogItemId, price, quantity);

        var mockBasketRepository = new Mock<IRepository<Basket>>();

        // Stub
        mockBasketRepository.Setup(x =>x.GetByIdAsync(It.Is<int>(id => id == deleteFromBasket.Id), ItIsAny_CT))
            .ReturnsAsync((Basket)deleteFromBasket);

        // Verifiable mock
        mockBasketRepository.Setup(x =>
            x.DeleteAsync(It.Is<Basket>(b => b.Id == deleteFromBasket.Id), ItIsAny_CT))
                .Returns(Task.CompletedTask)
                    .Verifiable(); 

        var basketService = new BasketService(mockBasketRepository.Object, new StubLogger<BasketService>());

        // Act
        await basketService.DeleteBasketAsync(deleteFromBasket.Id);

        // Assert 
        mockBasketRepository.Verify();
    }

    [Fact]
    public async Task BasketService_DeleteFromBasket_InvalidBasketId()
    {
        Assert.Fail("Test not written yet");
    }

    [Fact]
    public async Task BasketService_SetQuantities_IncreaseOk()
    {
        // Arrange
        var changeQuantityBasket = new Basket(username);
        changeQuantityBasket.AddItem(catalogItemId, price, quantity);

        var mockBasketRepository = new Mock<IRepository<Basket>>();

        // Stub
        mockBasketRepository.Setup(x => x.GetBySpecAsync(It.IsAny<BasketWithItemsSpecification>(), ItIsAny_CT))
            .ReturnsAsync(changeQuantityBasket);

        // Verifiable mock
        mockBasketRepository.Setup(x =>
            x.UpdateAsync(It.Is<Basket>(b => b.BuyerId == username), ItIsAny_CT))
                .Returns(Task.CompletedTask)
                    .Verifiable();

        var basketService = new BasketService(mockBasketRepository.Object, new StubLogger<BasketService>());

        quantity.Should().BeGreaterThan(0, "Test malfunction, expecting quantity to be greater than zero.");
        int expectedQuantity = quantity*2;
        var quantitiesToUpdate = new Dictionary<string, int> { { changeQuantityBasket.Items.First().Id.ToString(), newQuantity } };

        // Act
        var actual = await basketService.SetQuantities(changeQuantityBasket.Id, quantitiesToUpdate);

        // Assert 
        mockBasketRepository.Verify();
        actual.Items.First().Quantity.Should().Be(expectedQuantity);
        actual.Items.Count.Should().Be(1);
    }

    [Fact]
    public async Task BasketService_SetQuantities_DecreasedAndRemovedOK()
    {
        // Arrange
        var changeQuantityBasket = new Basket(username);
        changeQuantityBasket.AddItem(catalogItemId, price, quantity);

        var mockBasketRepository = new Mock<IRepository<Basket>>();

        // Stub
        mockBasketRepository.Setup(x => x.GetBySpecAsync(It.IsAny<BasketWithItemsSpecification>(), ItIsAny_CT))
            .ReturnsAsync(changeQuantityBasket);

        // Verifiable mock
        mockBasketRepository.Setup(x =>
            x.UpdateAsync(It.Is<Basket>(b => b.BuyerId == username), ItIsAny_CT))
                .Returns(Task.CompletedTask)
                    .Verifiable();

        var basketService = new BasketService(mockBasketRepository.Object, new StubLogger<BasketService>());

        const int zeroQuantity = 0; // Update to zero quantity, expect it to be removed
        var quantitiesToUpdate = new Dictionary<string, int> { { changeQuantityBasket.Items.First().Id.ToString(), zeroQuantity } };

        // Act
        var actual = await basketService.SetQuantities(changeQuantityBasket.Id, quantitiesToUpdate);

        // Assert 
        mockBasketRepository.Verify();
        actual.Items.Count.Should().Be(0);
    }

    [Fact]
    public async Task BasketService_SetQuantities_BadParameter1()
    {
        Assert.Fail("Test not written yet");
    }

    [Fact]
    public async Task BasketService_TransferBasketAsync_OK()
    {
        Assert.Fail("Test not written yet");
    }
}
