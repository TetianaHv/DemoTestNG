package ui.rozetkaPages.PO_PF_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.rozetkaPages.UsefulMethods;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CatalogFactoryPage extends UsefulMethods {
    WebDriver webDriver;
    WebDriverWait wait;

    public CatalogFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(partialLinkText = "Доставка")
    private WebElement waitingLocator;
    @FindBy(css = "span.goods-tile__price-value")
    private List<WebElement> monitorsPrice;
    @FindBy(xpath = "//a[@class='goods-tile__picture']")
    private List<WebElement> monitorsImage;
    @FindBy(xpath = "//span[@class='categories-filter__link-title' and contains(text(), 'Мобильные телефоны')]")
    private WebElement categoryInBlock;
    @FindBy(className = "sidebar-block")
    private WebElement sidebarBlock;
    @FindBy(xpath = "//div[@data-filter-name='producer']")
    private WebElement producerFilterBlock;
    @FindBy(className = "goods-tile__heading")
    private List<WebElement> goodsListNames;
    @FindBy(css = "span.goods-tile__price-value")
    private List<WebElement> goodsListPrice;
    @FindBy(xpath = "//div[@data-filter-name='price']")
    private WebElement priceFilter;
    @FindBy(xpath = "//input[@formcontrolname='min']")
    private WebElement minPriceField;
    @FindBy(xpath = "//input[@formcontrolname='max']")
    private WebElement maxPriceField;
    @FindBy(className = "slider-filter__button")
    private WebElement priceButton;
    @FindBy(xpath = "//span[@class='sidebar-block__toggle-title' and text()=' Встроенная память ']")
    private WebElement builtInMemoryBlock;

    public void waitForCatalogPageAppear() {
        wait.until(visibilityOf(waitingLocator));
    }

    public void findMonitor(String price) throws Exception {
        List<WebElement> webList = monitorsPrice;
        for (int i = 0; i <= webList.size(); i++) {
            String str = webList.get(i).getText().replaceAll(" ", "");
            if (i == webList.size()) {
                throw new Exception("There is no such monitor");
            }
            if (Integer.parseInt(price) > Integer.parseInt(str)) {
                List<WebElement> webList2 = monitorsImage;
                webList2.get(i).click();
                break;
            }
        }
    }

    public void waitForCategoryInBlockAppear() {
        wait.until(visibilityOf(categoryInBlock));
    }

    public void clickOnCategoryInBlock() {
        categoryInBlock.click();
    }

    public void waitForSidebarBlockAppear() {
        wait.until(visibilityOf(sidebarBlock));
    }

    public WebElement findProducerFilterBlock() {
        return producerFilterBlock;
    }

    public void checkProducerFilterCheckbox(String filter) {
        Actions act = new Actions(webDriver);
        act.moveToElement(webDriver.findElement(By.id(filter))).click().build().perform();
    }

    public void compareProductNamesByFilters(String filter1, String filter2, String filter3) throws Exception {
        List<WebElement> listOfNames = goodsListNames;
        for (WebElement listOfName : listOfNames) {
            String itemName = listOfName.getAttribute("title");
            if (itemName.contains(filter1) || itemName.contains(filter2) || itemName.contains(filter3)) {
                continue;
            } else {
                throw new Exception(itemName + " does not contain Samsung, Apple or Honor");
            }
        }
    }

    public WebElement findPriceFilter() {
        return priceFilter;
    }

    public void setMinPriceFilter(String price) {
        minPriceField.clear();
        minPriceField.sendKeys(price);
    }

    public void setMaxPriceFilter(String price) {
        maxPriceField.clear();
        maxPriceField.sendKeys(price);
    }

    public void clickPriceButton() {
        priceButton.click();
    }

    public void compareProductPricesByFilters(String minPrice, String maxPrice) throws Exception {
        List<WebElement> webList = goodsListPrice;
        for (WebElement webElement : webList) {
            String str = webElement.getText().replaceAll(" ", "");
            if (Integer.parseInt(minPrice) <= Integer.parseInt(str) && Integer.parseInt(maxPrice) >= Integer.parseInt(str)) {
                continue;
            } else {
                throw new Exception("Price " + str + " not included in the interval " + minPrice + " - " + maxPrice);
            }
        }
    }

    public WebElement findBuiltInMemoryBlock() {
        return builtInMemoryBlock;
    }

    public void checkBuiltInMemoryFilter(String filter) {
        WebElement checkboxBuiltInMemory = webDriver.findElement(By.id(filter));
        Actions act = new Actions(webDriver);
        act.moveToElement(checkboxBuiltInMemory).click().build().perform();
    }

    public void compareProductBuiltInMemoryValuesByFilters(String builtInMemoryValue) throws Exception {
        List<WebElement> webList = goodsListNames;
        for (WebElement webElement : webList) {
            String itemName = webElement.getAttribute("title");
            if (itemName.contains(builtInMemoryValue.substring(0, 2))) {
                continue;
            } else {
                throw new Exception(itemName + " does not contain " + builtInMemoryValue);
            }
        }
    }
}
