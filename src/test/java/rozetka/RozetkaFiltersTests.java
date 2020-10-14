package rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import seleniumDemoLesson.BaseUITest;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class RozetkaFiltersTests extends BaseUITest {
    String url = "https://rozetka.com.ua/";
    String searchText = "samsung";

    /*
1. Navigate to https://rozetka.com.ua/
2. Search by "samsung
3. Click "Мобильные телефоны" in the product filters panel
4. Add to filters "Apple" and "Honor"
5. Verify all filtered products are products made by Samsung, Apple or Honor
 */
    @Test
    public void productFilterTest() {
        driver.get(url);
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys(searchText + Keys.ENTER);
        By block = By.xpath("//span[@class='categories-filter__link-title' and contains(text(), 'Мобильные телефоны')]");
        wait.until(presenceOfElementLocated(block));
        driver.findElement(block).click();
        wait.until(presenceOfElementLocated(By.className("sidebar-block")));
        WebElement checkboxApple = driver.findElement(By.xpath("//input[@id='Apple']"));
        scrollToElement(checkboxApple);
        checkboxApple.click();

    }

    /*
    1. Navigate to https://rozetka.com.ua/
    2. Search by "samsung"
    3. Click "Мобильные телефоны" in the product filters panel
    4. Add to price filter: 5000<price<15000
    5. Verify all filtered products are products with price from range
     */
    @Test
    public void productPriceFilterTest() {
        driver.get(url);

    }

    /*
1. Navigate to https://rozetka.com.ua/
2. Search by "samsung"
3. Click "Мобильные телефоны" in the product filters panel
4. Add to filter:
5. Verify all filtered products
 */
    @Test
    public void productNFilterTest() {
        driver.get(url);

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

