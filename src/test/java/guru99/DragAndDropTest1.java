package guru99;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import seleniumDemoLesson.BaseUITest;

import static org.testng.Assert.assertTrue;

public class DragAndDropTest1 extends BaseUITest {

    @Test
    public void positiveDragAndDrop() {
        driver.get("http://demo.guru99.com/test/drag_drop.html");

        WebElement From1 = driver.findElement(By.xpath("//*[@id='credit2']/a"));
        WebElement To1 = driver.findElement(By.xpath("//*[@id='bank']/li"));
        WebElement From2 = driver.findElement(By.xpath("//*[@id='credit1']/a"));
        WebElement To2 = driver.findElement(By.xpath("//*[@id='loan']/li"));
        WebElement From3 = driver.findElement(By.xpath("//*[@id='fourth']/a"));
        WebElement To3 = driver.findElement(By.xpath("//*[@id='amt7']/li"));
        WebElement From4 = driver.findElement(By.xpath("//*[@id='fourth']/a"));
        WebElement To4 = driver.findElement(By.xpath("//*[@id='amt8']/li"));

        Actions act = new Actions(driver);
        act.dragAndDrop(From1, To1).build().perform();
        act.dragAndDrop(From2, To2).build().perform();
        act.dragAndDrop(From3, To3).build().perform();
        act.dragAndDrop(From4, To4).build().perform();

        assertTrue(driver.findElement(By.cssSelector("div.table4_result>a.button.button-green")).isDisplayed());
    }
}
