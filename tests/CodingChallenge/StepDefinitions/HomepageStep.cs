using CodingChallenge.Pages;
using Microsoft.Playwright;

namespace CodingChallenge.StepDefinitions;

[Binding]
public class HomepageStep
{
    private readonly Homepage _homepage;
    public HomepageStep(IPage page) 
    {
        _homepage = new Homepage(page);
    }

    [Given(@"I am on the HomePage")]
    public async Task GivenIAmOnTheHomePage()
    {
        await _homepage.NavigateTo();
    }

    [Given(@"I select (.*) items I like")]
    public async Task GivenISelectItemsILike(int numberOfItems)
    {
        await _homepage.AddItemsToBasket(numberOfItems);
    }

    [When(@"I select my shopping cart")]
    public async Task WhenISelectMyShoppingCart()
    {
        await _homepage.NavigateToBasketPage();
    }
}
