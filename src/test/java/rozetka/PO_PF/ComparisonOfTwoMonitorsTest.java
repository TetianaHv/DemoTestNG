package rozetka.PO_PF;

import org.testng.annotations.Test;
import rozetkaPages.PO_PF_pages.CatalogFactoryPage;
import rozetkaPages.PO_PF_pages.ComparisonFactoryPage;
import rozetkaPages.PO_PF_pages.MainFactoryPage;
import rozetkaPages.PO_PF_pages.ProductFactoryPage;
import seleniumDemoLesson.BaseUITest;

import static org.testng.Assert.assertEquals;

public class ComparisonOfTwoMonitorsTest extends BaseUITest {
    private String url = "https://rozetka.com.ua/";
    private String mainPrice = "3000";
    private String subcategoryName = "Мониторы";

    @Test
    public void monitorComparingTest() throws Exception {
        MainFactoryPage mainFactoryPage = new MainFactoryPage(driver);
        ProductFactoryPage productFactoryPage = new ProductFactoryPage(driver);
        ComparisonFactoryPage comparisonFactoryPage = new ComparisonFactoryPage(driver);
        CatalogFactoryPage catalogFactoryPage = new CatalogFactoryPage(driver);

        //Hover "Ноутбуки и компьютеры", click "Мониторы"
        mainFactoryPage.navigateToUrl(driver, url);
        mainFactoryPage.hoverMenu();
        mainFactoryPage.waitForSubcategoryNameAppear(subcategoryName);
        mainFactoryPage.clickOnSubcategoryMenu();
        //Find first monitor with price mainPrice less then 3000UAH
        catalogFactoryPage.waitForCatalogPageAppear();
        catalogFactoryPage.findMonitor(mainPrice);
        //Add monitor to comparison.
        // Verify icon (1) appears in header close to comparison image (scales).
        productFactoryPage.waitForProductPriceAppear();
        productFactoryPage.clickOnComparisonButton();
        productFactoryPage.waitForComparisonNumberAppear();
        assertEquals(productFactoryPage.getComparisonNumber(), "1");

        String monitorName1 = productFactoryPage.getProductName();
        String monitorPrice1 = productFactoryPage.getProductPrice().replaceAll(" ", "");
        //Click back button in browser
        catalogFactoryPage.navigateBack(driver);
        //Find first monitor which price is less then first monitor.
        // Click on image of found monitor. Wait for page to load
        catalogFactoryPage.waitForCatalogPageAppear();
        catalogFactoryPage.findMonitor(monitorPrice1.substring(0, 4));
        //Add second monitor to comparison.
        // Verify icon (2) appears in header close to comparison image (scales).
        productFactoryPage.waitForProductPriceAppear();
        productFactoryPage.clickOnComparisonButton();
        productFactoryPage.waitForUpdatedComparisonNumberAppear();
        assertEquals(productFactoryPage.getComparisonNumber(), "2");

        String monitorName2 = productFactoryPage.getProductName();
        String monitorPrice2 = productFactoryPage.getProductPrice().replaceAll(" ", "");
        //Click on comparison image in header.
        //Click on "Мониторы (2)".
        mainFactoryPage.clickOnComparisonImageInHeader();
        mainFactoryPage.clickOnComparisonSuite();
        //Verify that in comparison you have just 2 monitors
        comparisonFactoryPage.waitForProductsAppear();
        assertEquals(comparisonFactoryPage.getAllProducts().size(), 2);

        //Verify that names are correct (equal to names which you stored in step4 and step7)
        //Verify that prices are correct (equal to prices which you stored in step4 and step7)
        String productName1 = comparisonFactoryPage.getProductName(0);
        String productName2 = comparisonFactoryPage.getProductName(1);

        String productPrice1 = comparisonFactoryPage.getProductPrice(0);
        String productPrice2 = comparisonFactoryPage.getProductPrice(1);

        assertEquals(monitorName1, productName1);
        assertEquals(monitorName2, productName2);
        assertEquals(monitorPrice1, productPrice1);
        assertEquals(monitorPrice2, productPrice2);
    }
}
