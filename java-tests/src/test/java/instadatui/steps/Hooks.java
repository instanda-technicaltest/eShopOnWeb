package instadatui.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import utils.Utilities;

public class Hooks extends Utilities {

    @Before
    public void inIT(){
       selectBrowser();
       driver.manage().window().maximize();
       driver.get("https://localhost:5001/");
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}
