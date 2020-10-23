package rozetka.PO_PF;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rozetkaPages.PO_PF_pages.CatalogFactoryPage;
import rozetkaPages.PO_PF_pages.MainFactoryPage;
import seleniumDemoLesson.BaseUITest;

public class FiltersTests extends BaseUITest {
    private String url = "https://rozetka.com.ua/";
    private String searchText = "Samsung";
    private String producerFilter1 = "Apple";
    private String producerFilter2 = "Huawei";
    private String minPrice = "5000";
    private String maxPrice = "15000";
    private String builtInMemoryValue = "256 ГБ";
    MainFactoryPage mainFactoryPage;
    CatalogFactoryPage catalogFactoryPage;

    @BeforeMethod
    public void navigateToUrl() {
        mainFactoryPage.navigateToUrl(driver, url);
        mainFactoryPage = new MainFactoryPage(driver);
        catalogFactoryPage = new CatalogFactoryPage(driver);
        //2. Search by "samsung
        mainFactoryPage.enterSearchTextUsingSearch(searchText);
        catalogFactoryPage.waitForCategoryInBlockAppear();
        //3. Click "Мобильные телефоны" in the product filters panel
        catalogFactoryPage.clickOnCategoryInBlock();
        catalogFactoryPage.waitForSidebarBlockAppear();
    }

    @Test
    public void productFilterTest() throws Exception {
        //4. Add to filters "Apple"
        catalogFactoryPage.scrollToElement(catalogFactoryPage.findProducerFilterBlock(), driver);
        catalogFactoryPage.checkProducerFilterCheckbox(producerFilter1);
        //4. Add to filter "Honor" --"Huawei"
        catalogFactoryPage.waitForSidebarBlockAppear();
        catalogFactoryPage.scrollToElement(catalogFactoryPage.findProducerFilterBlock(), driver);
        catalogFactoryPage.checkProducerFilterCheckbox(producerFilter2);
        //5. Verify all filtered products are products made by Samsung, Apple or Honor
        catalogFactoryPage.waitForSidebarBlockAppear();
   //assertTrue     catalogFactoryPage.compareProductNamesByFilters(searchText, producerFilter1, producerFilter2);
    }

    @Test
    public void productPriceFilterTest() throws Exception {
        //4. Add to price filter: 5000<price
        catalogFactoryPage.scrollToElement(catalogFactoryPage.findPriceFilter(), driver);
        catalogFactoryPage.setMinPriceFilter(minPrice);
        //4. Add to price filter: price<15000
        catalogFactoryPage.setMaxPriceFilter(maxPrice);
        catalogFactoryPage.clickPriceButton();
        //5. Verify all filtered products are products with price from range
        catalogFactoryPage.waitForSidebarBlockAppear();
  //assertTrue      catalogFactoryPage.compareProductPricesByFilters(minPrice, maxPrice);
    }

    @Test
    public void productBuiltInMemoryFilterTest() throws Exception {
        //4. Add to filter: builtInMemoryValue = "256 ГБ"
        catalogFactoryPage.scrollToElement(catalogFactoryPage.findBuiltInMemoryBlock(), driver);
        catalogFactoryPage.checkBuiltInMemoryFilter(builtInMemoryValue);
        //5. Verify all filtered products
        catalogFactoryPage.waitForSidebarBlockAppear();
   //assertTrue     catalogFactoryPage.compareProductBuiltInMemoryValuesByFilters(builtInMemoryValue);
    }
}

