package rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumDemoLesson.BaseUITest;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class RozetkaFiltersTests extends BaseUITest {
    String url = "https://rozetka.com.ua/";
    String searchText = "samsung";
    String minPrice = "5000";
    String maxPrice = "15000";
    String builtInMemoryValue = "256 ГБ";

    @BeforeMethod
    public void startSteps() {
        driver.get(url);
        driver.findElement(By.name("search")).sendKeys(searchText + Keys.ENTER);
        By block = By.xpath("//span[@class='categories-filter__link-title' and contains(text(), 'Мобильные телефоны')]");
        wait.until(presenceOfElementLocated(block));
        driver.findElement(block).click();
        wait.until(presenceOfElementLocated(By.className("sidebar-block")));
    }

    /*
1. Navigate to https://rozetka.com.ua/
2. Search by "samsung
3. Click "Мобильные телефоны" in the product filters panel
4. Add to filters "Apple" and "Honor"
5. Verify all filtered products are products made by Samsung, Apple or Honor
 */
    @Test
    public void productFilterTest() throws Exception {
        scrollToElement(driver.findElement(By.xpath("//div[@data-filter-name='producer']")));
        WebElement checkboxApple = driver.findElement(By.id("Apple"));
        Actions act = new Actions(driver);
        act.moveToElement(checkboxApple).click().build().perform();

        wait.until(presenceOfElementLocated(By.className("sidebar-block")));
        scrollToElement(driver.findElement(By.xpath("//div[@data-filter-name='producer']")));
        WebElement checkboxHonor = driver.findElement(By.id("Honor"));
        act.moveToElement(checkboxHonor).click().build().perform();

        By list = By.className("goods-tile__heading");
        wait.until(presenceOfAllElementsLocatedBy(list));

        List<WebElement> listOfNames = driver.findElements(list);
        for (int i = 0; i < listOfNames.size(); i++) {
            String itemName = listOfNames.get(i).getAttribute("title");
            if (itemName.contains("Samsung") || itemName.contains("Apple") || itemName.contains("Honor")) {
                continue;
            } else {
                throw new Exception(itemName + " does not contain Samsung, Apple or Honor");
            }
        }
    }

    /*
    1. Navigate to https://rozetka.com.ua/
    2. Search by "samsung"
    3. Click "Мобильные телефоны" in the product filters panel
    4. Add to price filter: 5000<price<15000
    5. Verify all filtered products are products with price from range
     */
    @Test
    public void productPriceFilterTest() throws Exception {
        scrollToElement(driver.findElement(By.xpath("//div[@data-filter-name='price']")));
        WebElement setMinPrice = driver.findElement(By.xpath("//input[@formcontrolname='min']"));
        setMinPrice.clear();
        setMinPrice.sendKeys(minPrice);
        WebElement setMaxPrice = driver.findElement(By.xpath("//input[@formcontrolname='max']"));
        setMaxPrice.clear();
        setMaxPrice.sendKeys(maxPrice);
        driver.findElement(By.className("slider-filter__button")).click();

        wait.until(presenceOfAllElementsLocatedBy(By.cssSelector("span.goods-tile__price-value")));
        List<WebElement> webList = driver.findElements(By.cssSelector("span.goods-tile__price-value"));
        for (int i = 0; i < webList.size(); i++) {
            String str = webList.get(i).getText().replaceAll(" ", "");
            if (Integer.parseInt(minPrice) <= Integer.parseInt(str) || Integer.parseInt(maxPrice) >= Integer.parseInt(str)) {
                continue;
            } else {
                throw new Exception("Price " + str + " not included in the interval " + minPrice + " - " + maxPrice);
            }
        }
    }

    /*
1. Navigate to https://rozetka.com.ua/
2. Search by "samsung"
3. Click "Мобильные телефоны" in the product filters panel
4. Add to filter:
5. Verify all filtered products
 */
    @Test
    public void productBuiltInMemoryFilterTest() throws Exception {
        scrollToElement(driver.findElement(By.xpath("//span[@class='sidebar-block__toggle-title' and text()=' Встроенная память ']")));
        WebElement checkboxBuiltInMemory = driver.findElement(By.id(builtInMemoryValue));
        Actions act = new Actions(driver);
        act.moveToElement(checkboxBuiltInMemory).click().build().perform();

        By list = By.className("goods-tile__heading");
        wait.until(presenceOfElementLocated(By.linkText("Телефоны, наушники, GPS")));

        List<WebElement> listOfNames = driver.findElements(list);
        for (int i = 0; i < listOfNames.size(); i++) {
            String itemName = listOfNames.get(i).getAttribute("title");
            if (itemName.contains(builtInMemoryValue.substring(0, 2))) {
                continue;
            } else {
                throw new Exception(itemName + " does not contain " + builtInMemoryValue);
            }
        }
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

