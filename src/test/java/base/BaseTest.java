package base;

import driver.DriverManager;
import driver.TargetFactory;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.Log;
import utils.TestUtil;
import utils.WebEventListener;

import java.util.concurrent.TimeUnit;

import static config.ConfigurationManager.configuration;

public class BaseTest {

    private EventFiringWebDriver e_driver;
    private WebEventListener eventListener;


    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String browser) {

        Log.info("In Before Method");
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("Running on " + os + " Operating System");
        Log.info("Running on " + os + " Operating System");
        Log.info("Initializing driver for " + browser + " browser");

        WebDriver driver = new TargetFactory().createInstance(browser);
        DriverManager.setDriver(driver);
        driver = DriverManager.getDriver();
        Log.info(browser + " Driver value is -->" + driver);

        e_driver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;

        //Reading Property File using Owner, Using configuration static function here created under ConfigurationManager

        driver.get(configuration().url());
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.MILLISECONDS);
    }


    @Step("Taking Screenshot")
    @Attachment
    public synchronized byte[] screenShot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Parameters("browser")
    @Step("Closing Browser")
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result, String browser) throws InterruptedException {
        Log.info("In After Method");
        {
            if (result.getStatus() == ITestResult.FAILURE) {
                Log.info("=========Test Case " + result.getName() + " Failed=======");
                Log.info("Taking Screenshot");
                screenShot();
            } else {
                Log.info("In After Method, Test " + result.getName() + " is Ending");
                Log.info("Taking Screenshot");
                screenShot();
            }
            DriverManager.quit();
        }
    }

}