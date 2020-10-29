package ui.rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ui.seleniumDemoLesson.BaseUITest;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.assertEquals;

public class RozetkaComparisonOfTwoMonitorsTest extends BaseUITest {
    String url = "https://rozetka.com.ua/";
    String mainPrice = "3000";

    @Test
    public void monitorComparingTest() throws Exception {
//        driver.manage().window().maximize();
        driver.get(url);
        //Hover "Ноутбуки и компьютеры", click "Мониторы"
        Actions action = new Actions(driver);
        WebElement categories = driver.findElement(By.xpath("//a[@class='menu-categories__link' and contains(text(), 'Ноутбуки и компьютеры')]"));
        action.moveToElement(categories).build().perform();
        WebElement monitorPath = driver.findElement(By.xpath("//a[@class='menu__link' and contains(text(), 'Мониторы')]"));
        wait.until(textToBePresentInElement(monitorPath, "Мониторы"));
        monitorPath.click();
        //Find first monitor with price mainPrice less then 3000UAH
        wait.until(presenceOfElementLocated(By.partialLinkText("Доставка")));
        findMonitor(mainPrice);
        //Add monitor to comparison.
        // Verify icon (1) appears in header close to comparison image (scales).
        wait.until(presenceOfElementLocated(By.cssSelector("p.product-prices__big.product-prices__big_color_red")));
        driver.findElement(By.xpath("//button[@class='compare-button']")).click();
        By comparisonNumber = By.cssSelector("span.header-actions__button-counter");
        wait.until(presenceOfElementLocated(comparisonNumber));
        assertEquals(driver.findElement(comparisonNumber).getText(), "1");

        WebElement name = driver.findElement(By.tagName("h1"));
        String monitorName1 = name.getText();
        WebElement price = driver.findElement(By.cssSelector("p.product-prices__big.product-prices__big_color_red"));
        String monitorPrice1 = price.getText().replaceAll(" ", "");
        //Click back button in browser
        driver.navigate().back();
        //Find first monitor which price is less then first monitor.
        // Click on image of found monitor. Wait for page to load
        wait.until(presenceOfElementLocated(By.cssSelector("span.goods-tile__price-value")));
        findMonitor(monitorPrice1.substring(0, 4));
        //Add second monitor to comparison.
        // Verify icon (2) appears in header close to comparison image (scales).
        wait.until(presenceOfElementLocated(By.cssSelector("p.product-prices__big.product-prices__big_color_red")));
        driver.findElement(By.xpath("//button[@class='compare-button']")).click();
        WebElement comparisonNumber2 = driver.findElement(By.cssSelector("span.header-actions__button-counter"));
        wait.until(ExpectedConditions.not(textToBePresentInElement(comparisonNumber2, "1")));
        assertEquals(comparisonNumber2.getText(), "2");

        WebElement name2 = driver.findElement(By.className("product__title"));
        String monitorName2 = name2.getText();
        WebElement price2 = driver.findElement(By.cssSelector("p.product-prices__big.product-prices__big_color_red"));
        String monitorPrice2 = price2.getText().replaceAll(" ", "");
        //Click on comparison image in header.
        //Click on "Мониторы (2)".
        driver.findElement(By.className("header-actions__button-icon")).click();
        driver.findElement(By.cssSelector("a.comparison-modal__link")).click();
        //Verify that in comparison you have just 2 monitors
        wait.until(presenceOfElementLocated(By.cssSelector("li.products-grid__cell")));
        List<WebElement> productsList = driver.findElements(By.cssSelector("li.products-grid__cell"));
        assertEquals(productsList.size(), 2);

        //Verify that names are correct (equal to names which you stored in step4 and step7)
        //Verify that prices are correct (equal to prices which you stored in step4 and step7)
        String productName1 = productsList.get(0).findElement(By.className("product__heading")).getText();
        String productName2 = productsList.get(1).findElement(By.className("product__heading")).getText();

        String productPrice1 = productsList.get(0).findElement(By.cssSelector("div.product__price.product__price--red")).getText().replaceAll("\\s+", "");
        String[] productPrice1Split = productPrice1.split("₴");
        productPrice1 = productPrice1Split[1] + "₴";
        String productPrice2 = productsList.get(1).findElement(By.cssSelector("div.product__price.product__price--red")).getText().replaceAll("\\s+", "");
        String[] productPrice2Split = productPrice2.split("₴");
        productPrice2 = productPrice2Split[1] + "₴";

        assertEquals(monitorName1, productName1);
        assertEquals(monitorName2, productName2);
        assertEquals(monitorPrice1, productPrice1);
        assertEquals(monitorPrice2, productPrice2);
    }

    private void findMonitor(String price) throws Exception {
        List<WebElement> webList = driver.findElements(By.cssSelector("span.goods-tile__price-value"));
        for (int i = 0; i <= webList.size(); i++) {
            String str = webList.get(i).getText().replaceAll(" ", "");
            if (i == webList.size()) {
                throw new Exception("There is no such monitor");
            }
            if (Integer.parseInt(price) > Integer.parseInt(str)) {
                List<WebElement> webList2 = driver.findElements(By.xpath("//a[@class='goods-tile__picture']"));
                webList2.get(i).click();
                break;
            }
        }
    }
}
