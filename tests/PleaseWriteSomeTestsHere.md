# Unable to complete the set up for Docker due to restrictions on work laptop. Tried all work arounds, including attempting to install a local VM, again this was restricted due to not being able to enable visualisation aspect.

# Upon further discovery, was able to use the the code and write NUnit tests.

# Testing approach for these examples are around validation.

using Microsoft.eShopWeb.ApplicationCore.Entities;
using Microsoft.eShopWeb.ApplicationCore.Entities.BasketAggregate;
using Microsoft.eShopWeb.ApplicationCore.Interfaces;
using Microsoft.eShopWeb.Web.Services;
using Moq;
using NUnit.Framework;

[TestFixture]
public class WhenusingCatalogViewModelService
{
    private Mock<IRepository<CatalogItem>> _catalogItem;
    priavte Mock<ILoggerFactory> _loggerFactory;
    
    [Test]
    public WhenusingCatalogViewModelService()
    {
        var service = new CatalogViewModelService(_loggerFactory, _catalogItem);

        var pageIndex = 1;
        var rseult = service.GetCatalogItems(pageIndex, 2);

        //Check basic details from the response match
        Assert.AreEqual(pageIndex, rseult.PaginationInfo.ActualPage)
    }
}


[TestFixture]
public class WhenusingBasketViewModelService
{
    // Mock objects put here to be re-usable for multiple tests
    private Mock<IRepository<Basket>> _basketRepository;
    private Mock<IRepository<CatalogItem>> _itemRepository;
    private Mock<IUriComposer> _uriComposer;
    private Mock<IBasketQueryService> _basketQueryService;

    [SetUp]
    public void Setup()
    {
        // private mock repostitories set up here for use for multiple tests
        _basketRepository = new Mock<IRepository<Basket>>();
        _itemRepository = new Mock<IRepository<CatalogItem>>();
        _uriComposer = new Mock<IUriComposer>();
        _basketQueryService = new Mock<IBasketQueryService>();
    }

    [Test]
    public void WhenCallingMapOnBacketViewModelService()
    {
        var buyerId = "test";

        var service = new BasketViewModelService(_basketRepository.Object, _itemRepository.Object, _uriComposer.Object, _basketQueryService.Object);
        var basket = new Basket(buyerId);

        var result = service.Map(basket).Result;

        //Check basic details from the response match
        Assert.AreEqual(buyerId, result.BuyerId);
        Assert.AreEqual(0, result.Items.Count);
    }
}
