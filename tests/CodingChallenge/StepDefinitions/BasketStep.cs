using CodingChallenge.Pages;
using Microsoft.Playwright;


namespace CodingChallenge.StepDefinitions;

[Binding]
public class BasketStep
{
    private readonly BasketPage _basketPage;
    public BasketStep(IPage page) 
    {
        _basketPage = new BasketPage(page);  
    }

    [When(@"select Check out")]
    public async Task WhenSelectCheckOut()
    {
        await _basketPage.SelectCheckout();
    }

    [When(@"I select Pay now")]
    public async Task WhenISelectPayNow()
    {
        await _basketPage.SelectPayNow();
    }

    [Then(@"my order should be processed Successfully")]
    public async Task ThenMyOrderShouldBeProcessedSuccessfully()
    {
        var isOrderSuccessful = await _basketPage.IsOrderProcessedSuccessfully();

        isOrderSuccessful.Should().BeTrue();
    }



}
