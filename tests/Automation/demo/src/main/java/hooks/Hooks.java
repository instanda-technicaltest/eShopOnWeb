package hooks;

import commonFunctions.CommonFunctions;
import io.cucumber.java.After;
import io.cucumber.java.Before;

// 
public class Hooks {
    CommonFunctions commonFunctions = new CommonFunctions();

    // This will be called at starting to open the browser 
   @Before()
   public void beforeTest()
   {
       commonFunctions.openBrowse();
   }

    // This will be called after completion 
   @After
   public void afterTest()
   {
       commonFunctions.closeBrowser();
   }
}
