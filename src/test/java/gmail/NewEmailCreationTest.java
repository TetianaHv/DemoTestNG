package gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import seleniumDemoLesson.BaseUITest;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NewEmailCreationTest extends BaseUITest {
    String url = "https://stackoverflow.com";
    String gmailUrl = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
    String login = "test@gmail.com"; //not valid
    String password = "password"; //not valid
    String subject = "Test";
    String emailText = "Email body";
    String filePath = "/Users/user/Desktop/file.png"; //not valid
    String fileName = "file.png";

    @Test
    public void newEmailCreationTest() {
        //- Login to mail.google.com with existing account using stackoverflow.com
        driver.get(url);
        driver.findElement(By.cssSelector("a.login-link.s-btn.s-btn__filled.py8.js-gps-track")).click();
        driver.findElement(By.xpath("//button[@data-provider='google']")).click();
        //gmail login
        driver.findElement(By.name("identifier")).sendKeys(login + Keys.ENTER);
        By passwordField = By.name("password");
        wait.until(elementToBeClickable(passwordField));
        driver.findElement(passwordField).sendKeys(password + Keys.ENTER);
        wait.until(presenceOfElementLocated(By.id("notify-container")));
        //gmail
        driver.get(gmailUrl);
        wait.until(presenceOfElementLocated(By.xpath("//a[@aria-label='Inbox']")));
        //- Create new email (with TO=*the same email*, subject, email text)
        driver.findElement(By.xpath("//div[@role='button' and @style='user-select: none']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//div[@role='dialog']")));

        driver.findElement(By.xpath("//textarea[@aria-label='To']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subject);
        driver.findElement(By.xpath("//div[@aria-label = 'Message Body']")).sendKeys(emailText);
        // attach file from your local machine, click send
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath);
        wait.until(presenceOfElementLocated(By.xpath("//input[@name='attach']")));
        driver.findElement(By.xpath("//div[@role='button' and contains(text(), 'Send')]")).click();
        //- Go to inbox,
        driver.findElement(By.linkText("Inbox")).click();
        // verify email came
        By inboxButton = By.xpath("//a[@aria-label='Inbox 1 unread']");
        wait.until(presenceOfElementLocated(inboxButton));
        assertTrue(driver.findElement(inboxButton).isDisplayed());
        driver.findElement(By.xpath("//td[@tabindex='-1']")).click();
        // verify subject, content of email
        By subjectArea = By.xpath("//h2[contains(@data-thread-perm-id, 'thread')]");
        wait.until(presenceOfElementLocated(subjectArea));
        assertEquals(driver.findElement(subjectArea).getText(), subject);
        assertEquals(driver.findElement(By.xpath("//div[@style='display:']//div[@dir='ltr']")).getText(), emailText);
        // verify file is attached
        assertTrue(driver.findElement(By.xpath("//span[contains(@download_url, '" + fileName + "')]")).isDisplayed());
    }
}
