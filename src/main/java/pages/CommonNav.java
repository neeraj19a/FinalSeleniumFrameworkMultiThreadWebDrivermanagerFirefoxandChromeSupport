package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import utils.Log;

public class CommonNav extends BasePage {

    //*********Web Elements By Using Page Factory*********
    @FindBy(id = "mr-link-signin-1")
    private WebElement signIn;
    @FindBy(xpath = ".//a[@class='overlay-signup__logo']")
    private WebElement miroLogo;
    @FindBy(className = "overlay-signup__btn")
    private WebElement signUp;


    public LoginPage clickSign() {
        click(signIn);
        return new LoginPage();
    }

    /**
     * To Verify Common Nav Buttons on Miro Application, Miro Logo and Sign In Button
     */
    public void verifyMiroLogoandSignInButton() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(signIn.isDisplayed(), "Looks like Sign In button is not Displayed");
        softAssert.assertTrue(miroLogo.isDisplayed(), "Looks like Miro Logo is not Displayed");
        softAssert.assertAll();
    }


    /**
     * To Verify Common Nav Buttons on Miro Application, Miro Logo and Sign Up Button
     */
    @Step("Verify Miro Logo and SignUp Button")
    public void verifyMiroLogoandSignUpButton() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(signUp.isDisplayed(), "Looks like Sign Up Button is not Displayed");
        softAssert.assertTrue(miroLogo.isDisplayed(), "Looks like Miro Logo is not Displayed");
        softAssert.assertAll();
        Log.info("Common Nav looks fine , Miro Logo and SignUp Button is present");
    }
}
