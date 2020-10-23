package rozetkaPages.PO_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rozetkaPages.UsefulMethods;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class MainPage extends UsefulMethods {
    WebDriver webDriver;
    WebDriverWait wait;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
    }

    By mainCategory = By.xpath("//a[@class='menu-categories__link' and contains(text(), 'Ноутбуки и компьютеры')]");
    By subcategory = By.xpath("//a[@class='menu__link' and contains(text(), 'Мониторы')]");
    By comparisonButton = By.className("header-actions__button-icon");
    By comparisonSuite = By.cssSelector("a.comparison-modal__link");
    By searchButton = By.name("search");

    public void hoverMenu() {
        Actions action = new Actions(webDriver);
        WebElement categories = webDriver.findElement(mainCategory);
        action.moveToElement(categories).build().perform();
    }

    public void waitForSubcategoryNameAppear(String name) {
        wait.until(textToBePresentInElement(webDriver.findElement(subcategory), name));
    }

    public void clickOnSubcategoryMenu() {
        webDriver.findElement(subcategory).click();
    }

    public void clickOnComparisonImageInHeader() {
        webDriver.findElement(comparisonButton).click();
    }

    public void clickOnComparisonSuite() {
        webDriver.findElement(comparisonSuite).click();
    }

    public void enterSearchTextUsingSearch(String text) {
        webDriver.findElement(searchButton).sendKeys(text + Keys.ENTER);
    }
}
