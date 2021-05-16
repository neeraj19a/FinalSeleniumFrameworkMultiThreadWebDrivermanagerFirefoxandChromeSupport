package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Log;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(xpath = ".//div[@class='signup__container']/h1")
    private WebElement signInHeading;

    //*********Web Elements By Using Page Factory*********
    @FindBy(id = "kmq-google-button")
    private WebElement signUpWithGoogleButton;
    @FindBy(id = "kmq-slack-button")
    private WebElement signUpWithSlackButton;
    @FindBy(id = "kmq-office365-button")
    private WebElement signUpWithOffice365Button;
    @FindBy(id = "apple-auth")
    private WebElement signUpWithAppleButton;
    @FindBy(className = "signup__separator")
    private WebElement signUpSeperator;
    @FindBy(id = "email")
    private WebElement workEmail;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(className = "signup__submit")
    private WebElement signIn;
    @FindBy(xpath = ".//div[@class='signup__error-item']")
    private List<WebElement> signUpError;
    @FindBy(xpath = ".//div[@class='signup__recovery']/a")
    private WebElement forgotPasswordLink;
    @FindBy(xpath = ".//div[@class='signup__under-submit']/a")
    private WebElement signInWithSSO;

    //*********Constructor*********/
    public LoginPage() {
        super();
    }

    @Step("Enter email")
    public LoginPage enterEmailId(String email) {
        setText(workEmail, email);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String pwd) {
        setText(password, pwd);
        return this;
    }

    @Step("Click SignIn")
    public LoginPage clickSignIn() {
        waitelementToBeClickable(signIn);
        clickWithMouseActions(signIn);
        return this;
    }

    @Step("LogIn with Username and Password")
    public DashboardPage logIn(String existingUserEmail, String existingUserPassword) {
        waitForPageToLoad();
        enterEmailId(existingUserEmail);
        enterPassword(existingUserPassword);
        clickSignIn();
        waitForPageToLoad();
        if (containsExpectedURL("https://miro.com/login/") == true) {
            Log.info("Looks Like Login Page is Again loaded");
            enterEmailId(existingUserEmail);
            enterPassword(existingUserPassword);
            clickSignIn();
        } else {
            Log.info("URL is-->" + getUrl());
        }
        waitForPageToLoad();
        return new DashboardPage();
    }

    @Step("LogIn")
    public List<String> logInWithoutUsernameAndPassword(String username, String password) {
        waitForPageToLoad();
        enterEmailId(username);
        enterPassword(password);
        clickSignIn();
        waitForPageToLoad();
        if (containsExpectedURL("https://miro.com/login/") == true) {
            Log.info("Looks Like Login Page is Again loaded");
            enterEmailId(username);
            enterPassword(password);
            clickSignIn();
        } else {
            Log.info("URL is-->" + getUrl());

        }
        waitForPageToLoad();

        List<String> arrayList = getErrorMessages(signUpError);
        if (arrayList.size() > 0) {
            Log.info("Login Failed with Error Message-->" + arrayList);
        }
        return arrayList;
    }

    /**
     * To Extract Error Messages on LoginPage
     *
     * @param webElements
     * @return
     */
    @Step("Checking If Error Messages while LogIn")
    public List<String> getErrorMessages(List<WebElement> webElements) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (WebElement element : webElements) {
            Assert.assertTrue(element.isDisplayed(), "Looks like Error Messages is not Loaded Pls Check");
            arrayList.add(element.getText());
        }

        return arrayList;
    }

    /**
     * For Navigating To Forgot Password Page from Login Page
     *
     * @return
     */
    @Step("Click ForgotPasswordLink")
    public ForgotPasswordPage clickForgotPasswordLink() {
        waitForPageToLoad();
        click(forgotPasswordLink);
        waitForPageToLoad();
        Assert.assertTrue(containsExpectedURL("https://miro.com/recover/"), "Looks Like ForgotPassword page is not loaded");
        return new ForgotPasswordPage();
    }

    /**
     * To Verify LogIn Page has all required Page Elements
     *
     * @return
     */
    @Step("Verify Login Page")
    public LoginPage verifyLogInPage() {
        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertTrue(signInHeading.isDisplayed(), "Looks Like SignInText is not Displayed");
        softAssertion.assertEquals(signInHeading.getText(), "Sign in", "Looks like PlaceHolder Text for SignIn Heading is missing or wrong");
        softAssertion.assertTrue(signUpWithGoogleButton.isDisplayed(), "Looks Like SignIn with GoogleButton is not Displayed");
        softAssertion.assertTrue(signUpWithSlackButton.isDisplayed(), "Looks Like SignIn with Slack Button is not Displayed");
        softAssertion.assertTrue(signUpWithOffice365Button.isDisplayed(), "Looks Like SignIn with Office is not Displayed");
        softAssertion.assertTrue(signUpWithAppleButton.isDisplayed(), "Looks Like SignIn with Apple is not Displayed");
        softAssertion.assertEquals(signUpSeperator.getText(), "or use your email to sign in:", "Looks like PlaceHolder Text for Sign Up Seperator is missing or wrong");
        softAssertion.assertEquals(workEmail.getAttribute("placeholder"), "Work email", "Looks like PlaceHolder Text for Email is missing");
        softAssertion.assertEquals(password.getAttribute("placeholder"), "Password", "Looks Like PlaceHolder Text for Password is not correct");
        softAssertion.assertTrue(forgotPasswordLink.isDisplayed(), "Looks Like ForgotPassword Link is not Displayed");
        softAssertion.assertTrue(signIn.isDisplayed(), "Looks Like signIn button is not Displayed");
        softAssertion.assertTrue(signInWithSSO.isDisplayed(), "Looks Like Sign In with SSO is not  Displayed");
        softAssertion.assertAll();

        Log.info(" Login Page looks GOOD with all Page Elements Present");
        return new LoginPage();
    }
}
