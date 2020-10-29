package ui.rozetka.PO;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.rozetkaPages.PO_pages.CatalogPage;
import ui.rozetkaPages.PO_pages.MainPage;
import ui.seleniumDemoLesson.BaseUITest;

import static org.testng.Assert.assertTrue;

public class FiltersTests extends BaseUITest {
    private String url = "https://rozetka.com.ua/";
    private String searchText = "Samsung";
    private String producerFilter1 = "Apple";
    private String producerFilter2 = "Huawei";
    private String minPrice = "5000";
    private String maxPrice = "15000";
    private String builtInMemoryValue = "256 ГБ";
    MainPage mainPage;
    CatalogPage catalogPage;

    @BeforeMethod
    public void navigateToUrl() {
        mainPage.navigateToUrl(driver, url);
        mainPage = new MainPage(driver);
        catalogPage = new CatalogPage(driver);
        //2. Search by "samsung
        mainPage.enterSearchTextUsingSearch(searchText);
        catalogPage.waitForCategoryInBlockAppear();
        //3. Click "Мобильные телефоны" in the product filters panel
        catalogPage.clickOnCategoryInBlock();
        catalogPage.waitForSidebarBlockAppear();
    }

    @Test
    public void productFilterTest() throws Exception {
        //4. Add to filters "Apple"
        catalogPage.scrollToElement(catalogPage.findProducerFilterBlock(), driver);
        catalogPage.checkProducerFilterCheckbox(producerFilter1);
        //4. Add to filter "Honor" --"Huawei"
        catalogPage.waitForSidebarBlockAppear();
        catalogPage.scrollToElement(catalogPage.findProducerFilterBlock(), driver);
        catalogPage.checkProducerFilterCheckbox(producerFilter2);
        //5. Verify all filtered products are products made by Samsung, Apple or Honor
        catalogPage.waitForGoodsPriceAppear();
        assertTrue(catalogPage.isProductNameConsistsFilterName(searchText, producerFilter1, producerFilter2));
    }

    @Test
    public void productPriceFilterTest() throws Exception {
        //4. Add to price filter: 5000<price
        catalogPage.scrollToElement(catalogPage.findPriceFilter(), driver);
        catalogPage.setMinPriceFilter(minPrice);
        //4. Add to price filter: price<15000
        catalogPage.setMaxPriceFilter(maxPrice);
        catalogPage.clickPriceButton();
        //5. Verify all filtered products are products with price from range
        catalogPage.waitForGoodsPriceAppear();
   //assertTrue     catalogPage.compareProductPricesByFilters(minPrice, maxPrice);
    }

    @Test
    public void productBuiltInMemoryFilterTest() throws Exception {
        //4. Add to filter: builtInMemoryValue = "256 ГБ"
        catalogPage.scrollToElement(catalogPage.findBuiltInMemoryBlock(), driver);
        catalogPage.checkBuiltInMemoryFilter(builtInMemoryValue);
        //5. Verify all filtered products
        catalogPage.waitForCatalogPageAppear();
 //assertTrue       catalogPage.compareProductBuiltInMemoryValuesByFilters(builtInMemoryValue);
    }
}

