package rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import seleniumDemoLesson.BaseUITest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RozetkaComparisonOfTwoMonitorsTest extends BaseUITest {
    String url = "https://rozetka.com.ua/";
    String mainPrice = "3000";

    @Test
    public void monitorComparingTest() throws Exception {
//        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Hover "Ноутбуки и компьютеры", click "Мониторы"
        Actions action = new Actions(driver);
        WebElement categories = driver.findElement(By.xpath("//a[@class='menu-categories__link' and contains(text(), 'Ноутбуки и компьютеры')]"));
        action.moveToElement(categories).build().perform();
        wait.until(textToBePresentInElementLocated(By.xpath("//a[@class='menu__link' and contains(text(), 'Мониторы')]"), "Мониторы"));
        driver.findElement(By.xpath("//a[@class='menu__link' and contains(text(), 'Мониторы')]")).click();
        //Find first monitor with price mainPrice less then 3000UAH
        wait.until(presenceOfAllElementsLocatedBy(By.cssSelector("span.goods-tile__price-value")));
        findMonitor(mainPrice);
        //Add monitor to comparison.
        // Verify icon (1) appears in header close to comparison image (scales).
        // Remember price, name
        wait.until(presenceOfElementLocated(By.xpath("//p[contains(@class, 'product-prices__big') and contains(@class, 'product-prices__big_color_red')]")));
        driver.findElement(By.xpath("//button[@class='compare-button']")).click();
        WebElement comparisonNumber = driver.findElement(By.cssSelector("span.header-actions__button-counter"));
        assertEquals(comparisonNumber.getText(), "1");

        WebElement name = driver.findElement(By.tagName("h1"));
        String monitorName = name.getText();
        WebElement price = driver.findElement(By.xpath("//p[contains(@class, 'product-prices__big') and contains(@class, 'product-prices__big_color_red')]"));
        String monitorPrice = price.getText();
        //Click back button in browser
        driver.navigate().back();
        //Find first monitor which price is less then first monitor.
        // Click on image of found monitor. Wait for page to load
        wait.until(presenceOfAllElementsLocatedBy(By.cssSelector("span.goods-tile__price-value")));
        findMonitor(monitorPrice.substring(0, 5).replaceAll(" ", ""));
        //Add second monitor to comparison.
        // Verify icon (2) appears in header close to comparison image (scales).
        // Remember price, name
        driver.findElement(By.xpath("//button[@class='compare-button']")).click();
        wait.until(textToBePresentInElementLocated(By.cssSelector("span.header-actions__button-counter"), "2"));
        WebElement comparisonNumber2 = driver.findElement(By.cssSelector("span.header-actions__button-counter"));
        assertEquals(comparisonNumber2.getText(), "2");

        WebElement name2 = driver.findElement(By.className("product__title"));
        String monitorName2 = name2.getText();
        WebElement price2 = driver.findElement(By.xpath("//p[contains(@class, 'product-prices__big') and contains(@class, 'product-prices__big_color_red')]"));
        String monitorPrice2 = price2.getText();

        //Click on comparison image in header.
        //Click on "Мониторы (2)". Wait for page to load
        driver.findElement(By.className("header-actions__button-icon")).click();
        driver.findElement(By.cssSelector("a.comparison-modal__link")).click();
        //Verify that in comparison you have just 2 monitors
        List<WebElement> list = driver.findElements(By.cssSelector("li.products-grid__cell"));
        assertEquals(list.size(), 2);
        //Verify that names are correct (equal to names which you stored in step4 and step7)
        //Verify that prices are correct (equal to prices which you stored in step4 and step7)
        List<WebElement> pricesList = driver.findElements(By.xpath("//*/rz-compare-tile/div/div[2]/div[2]/div[1]/div"));
        List<WebElement> namesList = driver.findElements(By.xpath("//li//a[@class='product__heading']"));

        assertTrue(pricesList.get(0).getText().replaceAll(" ", "").contains(monitorPrice.replaceAll(" ", "")));
        assertTrue(pricesList.get(1).getText().replaceAll(" ", "").contains(monitorPrice2.replaceAll(" ", "")));
        assertEquals(monitorName, namesList.get(0).getText());
        assertEquals(monitorName2, namesList.get(1).getText());
    }

    private void findMonitor(String price) throws Exception {
        List<WebElement> webList = driver.findElements(By.cssSelector("span.goods-tile__price-value"));
        for (int i = 0; i <= webList.size(); i++) {
            String str = webList.get(i).getText().replaceAll(" ", "");
            if (i == webList.size()) {
                throw new Exception("There is no such monitor");
            }
            if (Integer.parseInt(price) > Integer.parseInt(str)) {
                List<WebElement> webList2 = driver.findElements(By.xpath(" //a[@class='goods-tile__picture']"));
                webList2.get(i).click();
                break;
            }
        }
    }
}
