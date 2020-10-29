package ui.rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ui.seleniumDemoLesson.BaseUITest;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class RozetkaBasketTest extends BaseUITest {
    String url = "https://rozetka.com.ua/";
    String searchText = "samsung";

    @Test
    public void basketIconTest() {
        driver.get(url);
        //list
        driver.findElement(By.name("search")).sendKeys(searchText + Keys.ENTER);
        By firstProductLink = By.cssSelector("a.goods-tile__picture");
        wait.until(presenceOfElementLocated(firstProductLink));
        driver.findElement(firstProductLink).click();
        //product
        By buyButtonSelector = By.cssSelector("button.buy-button.button.button_with_icon");
        wait.until(presenceOfElementLocated(buyButtonSelector));
        scrollToElement(driver.findElement(By.cssSelector("ul.product-statuses")));
        assertEquals(driver.findElements(By.xpath("//a[@href='https:/ui.rozetka.com.ua/cart/']/span")).size(), 0);
        driver.findElement(buyButtonSelector).click();

        //cart
        driver.findElement(By.cssSelector("button.modal__close")).click();

        WebElement basketIcon = driver.findElement(By.xpath("//a[@href='https:/ui.rozetka.com.ua/cart/']/span"));
        assertEquals(basketIcon.getText(), 1);
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
