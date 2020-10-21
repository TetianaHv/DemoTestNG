package rozetka.PO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rozetkaPages.PO_pages.CatalogPage;
import rozetkaPages.PO_pages.MainPage;
import seleniumDemoLesson.BaseUITest;

import static org.testng.Assert.assertTrue;

public class RozetkaFiltersTests extends BaseUITest {
    String url = "https://rozetka.com.ua/";
    String searchText = "Samsung";
    String producerFilter1 = "Apple";
    String producerFilter2 = "Huawei";
    String minPrice = "5000";
    String maxPrice = "15000";
    String builtInMemoryValue = "256 ГБ";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void productFilterTest() throws Exception {
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        //2. Search by "samsung
        mainPage.findAllProductsBySearchText(searchText);
        catalogPage.waitForCategoryInBlockAppear();
        //3. Click "Мобильные телефоны" in the product filters panel
        catalogPage.clickOnCategoryInBlock();
        catalogPage.waitForSidebarBlockAppear();
        //4. Add to filters "Apple"
        scrollToElement(catalogPage.findProducerFilterBlock());
        catalogPage.checkProducerFilterCheckbox(producerFilter1);
        //4. Add to filter "Honor" --"Huawei"
        catalogPage.waitForSidebarBlockAppear();
        scrollToElement(catalogPage.findProducerFilterBlock());
        catalogPage.checkProducerFilterCheckbox(producerFilter2);
        //5. Verify all filtered products are products made by Samsung, Apple or Honor
        catalogPage.waitForGoodsPriceAppear();
        assertTrue(catalogPage.compareProductNamesByFilters(searchText, producerFilter1, producerFilter2));
    }

    @Test
    public void productPriceFilterTest() throws Exception {
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        //2. Search by "samsung"
        mainPage.findAllProductsBySearchText(searchText);
        catalogPage.waitForCategoryInBlockAppear();
        //3. Click "Мобильные телефоны" in the product filters panel
        catalogPage.clickOnCategoryInBlock();
        catalogPage.waitForSidebarBlockAppear();
        //4. Add to price filter: 5000<price
        scrollToElement(catalogPage.findPriceFilter());
        catalogPage.setMinPriceFilter(minPrice);
        //4. Add to price filter: price<15000
        catalogPage.setMaxPriceFilter(maxPrice);
        catalogPage.clickPriceButton();
        //5. Verify all filtered products are products with price from range
        catalogPage.waitForGoodsPriceAppear();
        assertTrue(catalogPage.compareProductPricesByFilters(minPrice, maxPrice));
    }

    @Test
    public void productBuiltInMemoryFilterTest() throws Exception {
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        //2. Search by "samsung
        mainPage.findAllProductsBySearchText(searchText);
        catalogPage.waitForCategoryInBlockAppear();
        //3. Click "Мобильные телефоны" in the product filters panel
        catalogPage.clickOnCategoryInBlock();
        catalogPage.waitForSidebarBlockAppear();
        //4. Add to filter: builtInMemoryValue = "256 ГБ"
        scrollToElement(catalogPage.findBuiltInMemoryBlock());
        catalogPage.checkBuiltInMemoryFilter(builtInMemoryValue);
        //5. Verify all filtered products
        catalogPage.waitForCatalogPageAppear();
        assertTrue(catalogPage.compareProductBuiltInMemoryValuesByFilters(builtInMemoryValue));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

