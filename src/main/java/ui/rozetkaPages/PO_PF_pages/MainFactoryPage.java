package ui.rozetkaPages.PO_PF_pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.rozetkaPages.UsefulMethods;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class MainFactoryPage extends UsefulMethods {
    WebDriver webDriver;
    WebDriverWait wait;

    public MainFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//a[@class='menu-categories__link' and contains(text(), 'Ноутбуки и компьютеры')]")
    private WebElement mainCategory;
    @FindBy(xpath = "//a[@class='menu__link' and contains(text(), 'Мониторы')]")
    private WebElement subcategory;
    @FindBy(className = "header-actions__button-icon")
    private WebElement comparisonButton;
    @FindBy(css = "a.comparison-modal__link")
    private WebElement comparisonSuite;
    @FindBy(name = "search")
    private WebElement searchButton;

    public void hoverMenu() {
        Actions action = new Actions(webDriver);
        action.moveToElement(mainCategory).build().perform();
    }

    public void waitForSubcategoryNameAppear(String name) {
        wait.until(textToBePresentInElement(subcategory, name));
    }

    public void clickOnSubcategoryMenu() {
        subcategory.click();
    }

    public void clickOnComparisonImageInHeader() {
        comparisonButton.click();
    }

    public void clickOnComparisonSuite() {
        comparisonSuite.click();
    }

    public void enterSearchTextUsingSearch(String text) {
        searchButton.sendKeys(text + Keys.ENTER);
    }
}
