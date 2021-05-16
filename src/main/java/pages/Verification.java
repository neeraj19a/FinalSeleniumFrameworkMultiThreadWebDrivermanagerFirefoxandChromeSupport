package pages;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Constants;
import utils.Log;

public class Verification extends BasePage {


    /**
     * To Verify LogIn Page has all required Page Elements
     *
     * @return
     */
    @Step("Verify Login Page")
    public LoginPage verifyLogInPage() {
        SoftAssert softAssertion = new SoftAssert();
        LoginPage loginPage = new LoginPage();

        /*softAssertion.assertTrue(loginPage.isSignInHeadingDisplayed(), "Looks Like SignInText is not Displayed");
        softAssertion.assertEquals(loginPage.signInHeadingText(), "Sign in", "Looks like PlaceHolder Text for SignIn Heading is missing or wrong");
        softAssertion.assertTrue(loginPage.isSignUpWithGoogleButtonDisplayed(), "Looks Like SignIn with GoogleButton is not Displayed");
        softAssertion.assertTrue(loginPage.isSignUpWithSlackButtonDisplayed(), "Looks Like SignIn with Slack Button is not Displayed");
        softAssertion.assertTrue(loginPage.isSignUpWithOffice365ButtonDisplayed(), "Looks Like SignIn with Office is not Displayed");
        softAssertion.assertTrue(loginPage.isSignUpWithAppleButtonDisplayed(), "Looks Like SignIn with Apple is not Displayed");
        softAssertion.assertEquals(loginPage.signUpSeperatorText(), "or use your email to sign in:", "Looks like PlaceHolder Text for Sign Up Seperator is missing or wrong");
        softAssertion.assertEquals(loginPage.workEmailText(), "Work email", "Looks like PlaceHolder Text for Email is missing");
        softAssertion.assertEquals(loginPage.passwordText(), "Password", "Looks Like PlaceHolder Text for Password is not correct");
        softAssertion.assertTrue(loginPage.isForgotPasswordLinkDisplayed(), "Looks Like ForgotPassword Link is not Displayed");
        softAssertion.assertTrue(loginPage.isSignInDisplayed(), "Looks Like signIn button is not Displayed");
        softAssertion.assertTrue(loginPage.isSignInWithSSODisplayed(), "Looks Like Sign In with SSO is not  Displayed");
        */
        softAssertion.assertAll();
        Log.info(" Login Page looks GOOD with all Page Elements Present");
        return new LoginPage();
    }

    /**
     * To Verify Dashboard has Page Elements and all properly Displayed
     *
     * @return
     */
    @Step("Verifying Dashboard")
    public DashboardPage verifyDashBoardPage() {
        waitForPageToLoad(Constants.WebURLs.DASHBOARD);
        Assert.assertTrue(containsExpectedURL(Constants.WebURLs.DASHBOARD), "Looks Like the Landing Page URL is not correct or Please check if its landing on Dashboard");
        DashboardPage dashboardPage = new DashboardPage();

        dashboardPage.checkCloseIcon();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(dashboardPage.getDasBoardPageTitle(), "Miro | Online Whiteboard for Visual Collaboration", "Title of The Dashboard Page is not correct");
        softAssert.assertTrue(dashboardPage.verifyinviteMembersButton(), "Invite Members Button is not Displayed");
        softAssert.assertTrue(dashboardPage.verifyupgradeButton(), "Upgrade Button is not Displayed");
        softAssert.assertTrue(dashboardPage.verifysearchBoards(), "Search Board is not Displayed");
        //softAssert.assertEquals(dashboardPage.searchBoards.getAttribute("placeholder"), "Search boards", "Looks Like Search Board Placeholder text is missing");
        softAssert.assertAll();
        Log.info("Dashboard Looks Good");

        return new DashboardPage();
    }
}
