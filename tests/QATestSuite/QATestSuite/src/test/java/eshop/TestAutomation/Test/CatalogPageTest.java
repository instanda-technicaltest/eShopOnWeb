package eshop.TestAutomation.Test;

import eshop.TestAutomation.TestComponents.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class CatalogPageTest extends BaseTest {
    public static Logger log = LogManager.getLogger(CatalogPageTest.class.getName());
    @Test
    public void verifyCatalogListDisplayed(){
        int totalItems = catalogPage.getNumberOfItemsdisplayedinCatalog();
        if(totalItems == 0){
            log.warn("No Items are displayed in catalog");
        }
    }
}
