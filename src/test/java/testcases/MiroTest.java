package testcases;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CommonNav;
import pages.ForgotPasswordSuccessPage;
import pages.LoginPage;

import java.util.List;

import static config.ConfigurationManager.configuration;

public class MiroTest extends BaseTest {


    /**
     * This Test verifies Login Page and its Page Elements are present
     **/
    @Severity(SeverityLevel.BLOCKER)
    @Feature("SignIn")
    @Story("Verify LogIn Page")
    //@Test(description = "Verify Login Page Has All Page Elements")
    public void verifyLoginPage() {

        //Verifying Common Nav Buttons
        CommonNav commonNav = new CommonNav();
        commonNav.verifyMiroLogoandSignUpButton();

        //Verifying Login Page
        LoginPage loginPage = new LoginPage();
        loginPage.verifyLogInPage();

    }

    /**
     * This test is used to verify Login Functionality with Correct Email and Password
     **/
    @Severity(SeverityLevel.BLOCKER)
    @Feature("SignIn")
    @Story("Verify LogIn Page")
    //@Test(priority = 2, dataProvider = "ValidUserDetails", dataProviderClass = Data.class, description = "Verify SignIn With Email and Password ")
    public void signInWithValidEmailandPassword(String email, String password) {

        //Verifying User is able To Login with Correct Username and Password and user lands on Dashboard
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(email, password).
                verifyDashBoardPage();

    }

    @Feature("SignIn")
    @Story("Verify LogIn Page")
    //@Test(priority = 3, description = "Verify SignIn Without Email and Password ")
    public void signInWithoutEmailandPassword() {

        //Extracting Error Message
        LoginPage loginPage = new LoginPage();
        List<String> errorMessages = loginPage.
                logInWithoutUsernameAndPassword("", "");

        //Assertion for Error Message (Verifying Error Message is shown when user doesn't enter Email  and password)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errorMessages.contains("Please enter your email address."), "Looks like Error message for Email address is not correct");
        softAssert.assertTrue(errorMessages.contains("Please enter your password."), "Looks like Error message for Password is not correct");
        softAssert.assertAll();
    }


    /**
     * This test verifies Existing user with Correct Email but wrong password
     */
    @Feature("SignIn")
    @Story("Verify LogIn Page")
    //@Test(priority = 4, description = "Verify SignIn With Correct Email and Wrong Password ")
    public void signInWithCorrectEmailandWrongPassword() {

        LoginPage loginPage = new LoginPage();
        //Extracting Error Message
        //Fetching Username and Password from property file using OWNER Library , using static configuration object
        List<String> errorMessages = loginPage.
                logInWithoutUsernameAndPassword(configuration().username(), "xyz");

        //Assertion for Error Message
        Assert.assertTrue(errorMessages.contains("The email or password you entered is incorrect.\n" +
                "Please try again."), "Looks like Error message for Password is not correct");

    }

    /**
     * This test Verifies Error messages for Incorrect Email and Password
     */
    @Feature("SignIn")
    @Story("Verify LogIn Page")
    //@Test(priority = 5, dataProvider = "InValidUserDetails", dataProviderClass = Data.class, description = "Verify SignIn With Incorrect Email and Password ")
    public void signInWithInCorrectEmailandPassword(String email, String password) {

        LoginPage loginPage = new LoginPage();
        //Extracting Error Message
        List<String> errorMessages = loginPage.
                logInWithoutUsernameAndPassword(email, password);

        //Assertion for Error Message
        Assert.assertTrue(errorMessages.contains("The email or password you entered is incorrect.\n" +
                "Please try again."), "Looks like Error message for Password is not correct");

    }

    /**
     * This test Verifies ForgotPasswordLink
     */
    @Feature("SignIn")
    @Story("Verify LogIn Page")
    @Test(priority = 6, description = "Verify ForgotPassword Link ")
    public void verifyForgotPasswordLink() {

        //Verifying ForgotPasswordPage and ForgotPasswordSuccessPage
        //Fetching Username and Password from property file using OWNER Library , using static configuration object
        new LoginPage().
                clickForgotPasswordLink().
                verifyForgotPasswordPage().
                enterEmailForRecovery(configuration().username()).
                verifyForgotPageSuccessPage();

        ForgotPasswordSuccessPage passwordSuccessPage = new ForgotPasswordSuccessPage();

        //Assertions for ForgotPassword Success Page Message
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(passwordSuccessPage.getThanksText(), "Thank you!", "Looks like the Thanks Message is not correct");
        softAssert.assertEquals(passwordSuccessPage.passwordResetText(), "We've sent password reset instructions to your email address. If no email is received within ten minutes, check that the submitted address is correct.", "Looks like the Password Reset Text is not correct");
        softAssert.assertAll();
    }

}
