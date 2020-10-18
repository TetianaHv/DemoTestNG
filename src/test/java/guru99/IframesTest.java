package guru99;

import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import seleniumDemoLesson.BaseUITest;

import java.awt.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class IframesTest extends BaseUITest {
    String url = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";

    @Test
    public void iframesTest() {
        //- Navigate to http://demo.guru99.com/Agile_Project/Agi_V1/index.php
        driver.get(url);
        //- Find iframe with id=primis_playerSekindoSPlayer...
        By iframeLoc = By.cssSelector("div[id^=primis_playerSekindoSPlayer]");
        wait.until(presenceOfElementLocated(iframeLoc));
        WebElement iframe = driver.findElement(iframeLoc);
        //- Click on play button
        Actions action = new Actions(driver);
        action.moveToElement(iframe).build().perform();
        driver.findElement(By.id("playBtn")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("adBreakPreloader")));
        //- While video is playing move cursos in and out iframe
        //- Verify that when cursor is hovering iframe then stop button is visible,
        action.moveToElement(iframe).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("leftArrow")));
        Point coordinates = iframe.getLocation();
//        action.moveByOffset(coordinates.getX(), coordinates.getY()+80).contextClick().build().perform();
        try {
            Robot robot = new Robot();
            action.moveByOffset(1,1);
            action.moveToElement(iframe);
            action.moveByOffset(1,1).perform();
            robot.mouseMove(coordinates.getX()+8, coordinates.getY() + 60);


            Locatable hoverItem = (Locatable) iframe;
            Mouse mouse = ((HasInputDevices) driver).getMouse();
            mouse.mouseMove(hoverItem.getCoordinates());


        } catch (AWTException e) {
            e.printStackTrace();
        }


//        By pauseButtonLoc = By.id("pauseBtn");
//        action.moveToElement(iframe).build().perform();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("leftArrow")));
//        assertTrue(driver.findElement(pauseButtonLoc).isDisplayed());
//        // when cursor is not hovering - non visible
//        action.moveToElement(driver.findElement(By.name("btnLogin")));
//        wait.until(ExpectedConditions.not(elementToBeClickable(pauseButtonLoc)));
//        assertFalse(driver.findElement(pauseButtonLoc).isDisplayed());
//        action.moveToElement(iframe).build().perform();
//        // when cursor is hovering - visible
//        action.moveToElement(iframe).build().perform();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("leftArrow")));
//        assertTrue(driver.findElement(pauseButtonLoc).isDisplayed());
    }
}
