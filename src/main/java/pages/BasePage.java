package pages;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import static config.ConfigurationManager.configuration;
import static org.openqa.selenium.support.PageFactory.initElements;

public class BasePage {

    public WebDriver driver = DriverManager.getDriver();
    JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();

    //configuration static object to read from Property File
    protected BasePage() {
        initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), configuration().timeout()), this);
    }

    public void click(WebElement element) {
        try {
            waitelementToBeClickable(element);
            element.click();
        } catch (Exception e) {
            Log.info("Exception found on clicking Element-->" + element + "-->" + e);
            executor.executeScript("arguments[0].click();", element);
        }
    }

    public void clickWithMouseActions(WebElement element) {
        waitelementToBeVisible(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build();
        waitelementToBeClickable(element);
        element.click();
    }

    public void setText(WebElement element, String text) {
        try {
            waitelementToBeClickable(element);
            element.click();
            element.sendKeys(text);
        } catch (Exception e) {
            Log.info("Exception found-->" + e);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].value='" + text + "';", element);
        }
    }


    public boolean waitForPageToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 45);
        return wait.until(new ExpectedCondition<Boolean>() {
                              public Boolean apply(WebDriver d) {
                                  boolean flag = (((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
                                  return flag;
                              }
                          }
        );
    }

    public boolean waitForPageToLoad(final String pageURL) {

        WebDriverWait wait = new WebDriverWait(driver, 45);
        return wait.until(new ExpectedCondition<Boolean>() {
                              public Boolean apply(WebDriver d) {

                                  boolean flag = (((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
                                  boolean flag1 = driver.getCurrentUrl().contains(pageURL);
                                  return flag && flag1;

                              }
                          }
        );
    }

    public void selectByVisibleText(WebElement webElement, String value) {
        Select select = new Select(webElement);
        select.selectByVisibleText(value);
    }

    public String getText(WebElement element) {
        waitelementToBeVisible(element);
        return element.getText();
    }

    public boolean isElementDisplayed(WebElement element) {
        waitelementToBeVisible(element);
        return element.isDisplayed();
    }

    public boolean containsURL(String url) {

        return driver.getCurrentUrl().contains(url);
    }

    public boolean containsExpectedURL(String url) {

        return getUrl().equals(url);
    }

    public String returnTitle() {

        return driver.getTitle();
    }


    /**
     * Utility function to switch to recent Open Window
     */
    public void switchToWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    /**
     * Utility Function to switch between Windows
     *
     * @param windowName
     */
    public void switchToWindow(String windowName) {
        driver.switchTo().window(windowName);

    }

    /**
     * Explicit Wait Utility function to wait for particular element to be clickable
     *
     * @param element
     */
    public void waitelementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 45);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * Explicit Wait Utility function to wait for particular element to be visible
     *
     * @param element
     */
    public void waitelementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 45);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Function to get URL of the current page
     *
     * @return
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Function to get Xpath of Dynamic Element
     *
     * @param xpathValue
     * @param substitutionValue
     * @return
     */
    public WebElement prepareWebElementWithDynamicXpath(String xpathValue, String substitutionValue) {
        return driver.findElement(By.xpath(xpathValue.replaceAll("xxxxx", substitutionValue)));
    }

    public void closeWindow() {
        driver.close();
    }

    public void refresh() {
        driver.navigate().refresh();
    }


}