package rozetkaPages.PO_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CatalogPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public CatalogPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
    }

    By waitingLocator = By.partialLinkText("Доставка");
    By monitorsPrice = By.cssSelector("span.goods-tile__price-value");
    By monitorsImage = By.xpath("//a[@class='goods-tile__picture']");
    By categoryInBlock = By.xpath("//span[@class='categories-filter__link-title' and contains(text(), 'Мобильные телефоны')]");
    By sidebarBlock = By.className("sidebar-block");
    By producerFilterBlock = By.xpath("//div[@data-filter-name='producer']");
    By goodsListNames = By.className("goods-tile__heading");
    By goodsListPrice = By.cssSelector("span.goods-tile__price-value");
    By priceFilter = By.xpath("//div[@data-filter-name='price']");
    By minPriceField = By.xpath("//input[@formcontrolname='min']");
    By maxPriceField = By.xpath("//input[@formcontrolname='max']");
    By priceButton = By.className("slider-filter__button");
    By builtInMemoryBlock = By.xpath("//span[@class='sidebar-block__toggle-title' and text()=' Встроенная память ']");

    public void waitForCatalogPageAppear() {
        wait.until(presenceOfElementLocated(waitingLocator));
    }

    private List<WebElement> getAllMonitorsPrices() {
        return webDriver.findElements(monitorsPrice);
    }

    private List<WebElement> getAllMonitorsImages() {
        return webDriver.findElements(monitorsImage);
    }

    public void findMonitor(String price) throws Exception {
        List<WebElement> webList = getAllMonitorsPrices();
        for (int i = 0; i <= webList.size(); i++) {
            String str = webList.get(i).getText().replaceAll(" ", "");
            if (i == webList.size()) {
                throw new Exception("There is no such monitor");
            }
            if (Integer.parseInt(price) > Integer.parseInt(str)) {
                List<WebElement> webList2 = getAllMonitorsImages();
                webList2.get(i).click();
                break;
            }
        }
    }

    public void waitForCategoryInBlockAppear() {
        wait.until(presenceOfElementLocated(categoryInBlock));
    }

    public void clickOnCategoryInBlock() {
        webDriver.findElement(categoryInBlock).click();
    }

    public void waitForSidebarBlockAppear() {
        wait.until(presenceOfElementLocated(sidebarBlock));
    }

    public WebElement findProducerFilterBlock() {
        return webDriver.findElement(producerFilterBlock);
    }

    public void checkProducerFilterCheckbox(String filter) {
        Actions act = new Actions(webDriver);
        act.moveToElement(webDriver.findElement(By.id(filter))).click().build().perform();
    }

    public void waitForGoodsPriceAppear() {
        wait.until(presenceOfAllElementsLocatedBy(goodsListPrice));
    }

    public void compareProductNamesByFilters(String filter1, String filter2, String filter3) throws Exception {
        List<WebElement> listOfNames = webDriver.findElements(goodsListNames);
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
        return webDriver.findElement(priceFilter);
    }

    public void setMinPriceFilter(String price) {
        WebElement setMinPrice = webDriver.findElement(minPriceField);
        setMinPrice.clear();
        setMinPrice.sendKeys(price);
    }

    public void setMaxPriceFilter(String price) {
        WebElement setMinPrice = webDriver.findElement(maxPriceField);
        setMinPrice.clear();
        setMinPrice.sendKeys(price);
    }

    public void clickPriceButton() {
        webDriver.findElement(priceButton).click();
    }

    public void compareProductPricesByFilters(String minPrice, String maxPrice) throws Exception {
        List<WebElement> webList = webDriver.findElements(goodsListPrice);
        for (int i = 0; i < webList.size(); i++) {
            String str = webList.get(i).getText().replaceAll(" ", "");
            if (Integer.parseInt(minPrice) <= Integer.parseInt(str) || Integer.parseInt(maxPrice) >= Integer.parseInt(str)) {
                continue;
            } else {
                throw new Exception("Price " + str + " not included in the interval " + minPrice + " - " + maxPrice);
            }
        }
    }

    public WebElement findBuiltInMemoryBlock() {
        return webDriver.findElement(builtInMemoryBlock);
    }

    public void checkBuiltInMemoryFilter(String filter) {
        WebElement checkboxBuiltInMemory = webDriver.findElement(By.id(filter));
        Actions act = new Actions(webDriver);
        act.moveToElement(checkboxBuiltInMemory).click().build().perform();
    }

    public void compareProductBuiltInMemoryValuesByFilters(String builtInMemoryValue) throws Exception {
        List<WebElement> webList = webDriver.findElements(goodsListNames);
        for (int i = 0; i < webList.size(); i++) {
            String itemName = webList.get(i).getAttribute("title");
            if (itemName.contains(builtInMemoryValue.substring(0, 2))) {
                continue;
            } else {
                throw new Exception(itemName + " does not contain " + builtInMemoryValue);
            }
        }
    }
}
