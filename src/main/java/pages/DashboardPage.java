package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Log;

public class DashboardPage extends BasePage {

    //*********Web Elements By Using Page Factory*********
    @FindBy(xpath = "(.//*[@class='rtb-btn rtb-btn--primary-bordered--light-edition rtb-btn--small invite-button'])[1]")
    private WebElement inviteMembers;
    @FindBy(xpath = "(.//*[@class='rtb-btn rtb-btn--small upgrade-button rtb-btn--primary'])[1]")
    private WebElement upgrade;
    @FindBy(xpath = ".//input[@class='boards-search__input ng-pristine ng-untouched ng-valid ng-empty boards-search__input--newest']")
    private WebElement searchBoards;
    @FindBy(xpath = "(.//*[@class='icon'])[1]")
    private WebElement closeIcon;


    public String getDasBoardPageTitle() {
        return returnTitle();
    }

    /**
     * To Verify Invite Members Button is Displayed on Dashboard
     *
     * @return
     */
    @Step("Verify InviteMembers Button")
    public boolean verifyinviteMembersButton() {
        waitelementToBeVisible(inviteMembers);
        return isElementDisplayed(inviteMembers);
    }

    @Step("Checking if Close icon appears")
    public void checkCloseIcon() {
        boolean flag = closeIcon.isDisplayed();
        if (flag == true) {
            click(closeIcon);
        } else {
            Log.info("Close Icon is not getting displayed, skipping closeIcon");
        }
    }

    /**
     * To Verify Upgrade Button is Displayed on Dashboard
     *
     * @return
     */
    @Step("Verify Upgrade Button")
    public boolean verifyupgradeButton() {
        return isElementDisplayed(upgrade);
    }

    /**
     * To Verify Search Boards is Displayed on Dashboard
     *
     * @return
     */
    @Step("Verify Search board")
    public boolean verifysearchBoards() {
        return isElementDisplayed(searchBoards);
    }

    /**
     * To Verify Dashboard has Page Elements and all properly Displayed
     *
     * @return
     */
    @Step("Verifying Dashboard")
    public DashboardPage verifyDashBoardPage() {
        waitForPageToLoad("https://miro.com/app/dashboard/");
        Assert.assertTrue(containsExpectedURL("https://miro.com/app/dashboard/"), "Looks Like the Landing Page URL is not correct or Please check if its landing on Dashboard");

        checkCloseIcon();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getDasBoardPageTitle(), "Miro | Online Whiteboard for Visual Collaboration", "Title of The Dashboard Page is not correct");
        softAssert.assertTrue(verifyinviteMembersButton(), "Invite Members Button is not Displayed");
        softAssert.assertTrue(verifyupgradeButton(), "Upgrade Button is not Displayed");
        softAssert.assertTrue(verifysearchBoards(), "Search Board is not Displayed");
        softAssert.assertEquals(searchBoards.getAttribute("placeholder"), "Search boards", "Looks Like Search Board Placeholder text is missing");
        softAssert.assertAll();
        Log.info("Dashboard Looks Good");

        return new DashboardPage();
    }
}
