package eshop.TestAutomation.Test;

import eshop.TestAutomation.TestComponents.BaseTest;
import org.testng.annotations.Test;

public class CatalogPageTest extends BaseTest {

    @Test
    public void verifyCatalogListDisplayed(){
        int totalItems = catalogPage.getNumberOfItemsdisplayedinCatalog();
        if(totalItems == 0){
            System.out.println("No Items are displayed in catalog");
        }
    }
}
