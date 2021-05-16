package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Log;

public class ForgotPasswordPage extends BasePage {

    @FindBy(xpath = ".//div[@class='signup__container']/h1")
    private WebElement pageHeading;

    //*********Web Elements By Using Page Factory*********
    @FindBy(xpath = "(.//div[@class='signup__container']/div)[1]")
    private WebElement enterEmailText;
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(className = "signup__submit")
    private WebElement continueButton;
    @FindBy(xpath = ".//div[@class='signup__footer signup__footer--text-center']/a")
    private WebElement backToSignIn;


    public String getForgotPasswordPageTitle() {
        return returnTitle();
    }

    public boolean isEmailTextBoxDisplayed() {
        return isElementDisplayed(email);
    }

    public boolean isContinueButtonDisplayed() {
        return isElementDisplayed(continueButton);
    }

    public boolean isbackToSignInLinkDisplayed() {
        return isElementDisplayed(backToSignIn);
    }

    /**
     * To verify Page Elements are present in Forgot Password Page
     *
     * @return
     */
    @Step("Verifying Forgot Password Page")
    public ForgotPasswordPage verifyForgotPasswordPage() {
        waitForPageToLoad();
        Assert.assertTrue(containsExpectedURL("https://miro.com/recover/"), "Looks Like the Forgot Password Page URL is not correct or Please check if its landing on Dashboard");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getForgotPasswordPageTitle(), "Online whiteboard & online collaboration tool | Miro", "Title of The Forgot Password Page is not correct");
        softAssert.assertTrue(isEmailTextBoxDisplayed(), "Email text box is not Displayed");
        softAssert.assertTrue(isContinueButtonDisplayed(), "Continue Button is not Displayed");
        softAssert.assertTrue(isbackToSignInLinkDisplayed(), "Back to SignIn Link is not Displayed");
        softAssert.assertEquals(pageHeading.getText(), "Password recovery", "Forgot Password Heading is not Correct");
        softAssert.assertEquals(enterEmailText.getText(), "Enter the email you're using for your account.", "Enter Email Text is not Correct");
        softAssert.assertAll();
        Log.info("Forgot Password Page Loaded Properly");

        return new ForgotPasswordPage();
    }

    /**
     * To Enter Email Address for Password Recovery
     *
     * @param emailAddress
     * @return
     */
    @Step("Enter Email for Password Recovery")
    public ForgotPasswordSuccessPage enterEmailForRecovery(String emailAddress) {
        waitForPageToLoad();
        setText(email, emailAddress);
        click(continueButton);
        return new ForgotPasswordSuccessPage();
    }


}
