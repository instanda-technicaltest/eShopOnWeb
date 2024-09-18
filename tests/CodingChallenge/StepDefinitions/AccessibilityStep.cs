using Deque.AxeCore.Commons;
using Deque.AxeCore.Playwright;
using Microsoft.Playwright;

namespace CodingChallenge.StepDefinitions;

[Binding]
public class AccessibilityStep
{
    private readonly IPage _page;
    private AxeResult? _axeResult;
    public AccessibilityStep(IPage page)
    {
        _page = page;
    }
    [GivenAttribute(@"I scan the page for accessibility violations")]
    public async Task GivenIScanThePageForAccessibilityViolations()
    {
        _axeResult = await _page.RunAxe();
        var seriousViolations = _axeResult.Violations
            .Where(v => v.Impact == "serious")
            .ToList();
       // _axeResult.Violations.Length.Should().Be(0);
    }
    
    [ThenAttribute(@"I expect there to be no volation")]
    public void  ThenIExpectThereToBeNoVolation()
    {
        var seriousViolations = _axeResult!.Violations
            .Where(v => v.Impact == "serious")
            .ToList();
        seriousViolations.Count.Should().Be(0);
    }
}
