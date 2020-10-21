package rozetka.PO;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rozetkaPages.PO_pages.CatalogPage;
import rozetkaPages.PO_pages.ComparisonPage;
import rozetkaPages.PO_pages.MainPage;
import rozetkaPages.PO_pages.ProductPage;
import seleniumDemoLesson.BaseUITest;

import static org.testng.Assert.assertEquals;

public class RozetkaComparisonOfTwoMonitorsTest extends BaseUITest {
    private String url = "https://rozetka.com.ua/";
    private String mainPrice = "3000";
    private String subcategoryName = "Мониторы";


    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void monitorComparingTest() throws Exception {
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        ProductPage productPage = new ProductPage(driver);
        ComparisonPage comparisonPage = new ComparisonPage(driver);

        //Hover "Ноутбуки и компьютеры", click "Мониторы"
        mainPage.hoverMenu();
        mainPage.waitForSubcategoryNameAppear(subcategoryName);
        mainPage.clickOnSubcategoryMenu();
        //Find first monitor with price mainPrice less then 3000UAH
        catalogPage.waitForCatalogPage();
        catalogPage.findMonitor(mainPrice);
        //Add monitor to comparison.
        // Verify icon (1) appears in header close to comparison image (scales).
        productPage.waitForProductPriceAppear();
        productPage.clickOnComparisonButton();
        productPage.waitForComparisonNumberAppear();
        assertEquals(productPage.getComparisonNumber(), "1");

        String monitorName1 = productPage.getProductName();
        String monitorPrice1 = productPage.getProductPrice().replaceAll(" ", "");
        //Click back button in browser
        driver.navigate().back();
        //Find first monitor which price is less then first monitor.
        // Click on image of found monitor. Wait for page to load
        //    wait.until(presenceOfElementLocated(By.cssSelector("span.goods-tile__price-value")));
        catalogPage.waitForCatalogPage();
        catalogPage.findMonitor(monitorPrice1.substring(0, 4));
        //Add second monitor to comparison.
        // Verify icon (2) appears in header close to comparison image (scales).
        productPage.waitForProductPriceAppear();
        productPage.clickOnComparisonButton();
        productPage.waitForUpdatedComparisonNumberAppear();
        assertEquals(productPage.getComparisonNumber(), "2");

        String monitorName2 = productPage.getProductName();
        String monitorPrice2 = productPage.getProductPrice().replaceAll(" ", "");
        //Click on comparison image in header.
        //Click on "Мониторы (2)".
        mainPage.clickOnComparisonImageInHeader();
        mainPage.clickOnComparisonSuite();
        //Verify that in comparison you have just 2 monitors
        comparisonPage.waitForProductsAppear();
        assertEquals(comparisonPage.getAllProducts().size(), 2);

        //Verify that names are correct (equal to names which you stored in step4 and step7)
        //Verify that prices are correct (equal to prices which you stored in step4 and step7)
        String productName1 = comparisonPage.getProductName(0);
        String productName2 = comparisonPage.getProductName(1);

        String productPrice1 = comparisonPage.getProductPrice(0);
        String productPrice2 = comparisonPage.getProductPrice(1);

        assertEquals(monitorName1, productName1);
        assertEquals(monitorName2, productName2);
        assertEquals(monitorPrice1, productPrice1);
        assertEquals(monitorPrice2, productPrice2);
    }
}
