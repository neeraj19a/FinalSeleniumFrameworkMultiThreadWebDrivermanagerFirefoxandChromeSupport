package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Log;

public class ForgotPasswordSuccessPage extends BasePage {

    //*********Web Elements By Using Page Factory*********
    @FindBy(xpath = ".//div[@class='signup__container']/form/button")
    private WebElement backToSignInButton;
    @FindBy(xpath = ".//div[@class='signup__container']/h1")
    private WebElement passwordResetThanksText;
    @FindBy(xpath = ".//div[@class='signup__container']/div")
    private WebElement passwordResetText;


    public boolean isbackToSignInButtonDisplayed() {
        return isElementDisplayed(backToSignInButton);
    }

    public boolean ispasswordResetThanksTextDisplayed() {
        return isElementDisplayed(passwordResetThanksText);
    }

    public boolean ispasswordResetTextDisplayed() {
        return isElementDisplayed(passwordResetText);
    }

    /**
     * To Verify Forgot Password Success Page Elements
     *
     * @return
     */
    @Step("Verify Forgot Password Success Page")
    public ForgotPasswordSuccessPage verifyForgotPageSuccessPage() {
        waitForPageToLoad();
        Assert.assertTrue(containsExpectedURL("https://miro.com/recover/success/"), "Looks Like the Forgot Password Success Page URL is not correct or Please check if its landing on Dashboard");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isbackToSignInButtonDisplayed(), "Back to Sign In Button is not Displayed");
        softAssert.assertTrue(ispasswordResetThanksTextDisplayed(), "Thanks Message is not Displayed");
        softAssert.assertTrue(ispasswordResetTextDisplayed(), "Password Reset text is not Displayed");
        softAssert.assertAll();
        Log.info("Forgot Password Page Loaded Properly");

        return new ForgotPasswordSuccessPage();
    }

    public String getThanksText() {
        return passwordResetThanksText.getText();
    }

    public String passwordResetText() {
        return passwordResetText.getText();
    }
}
