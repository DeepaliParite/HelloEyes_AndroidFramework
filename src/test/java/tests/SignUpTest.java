package tests;

import baseTest.BaseTest;
import pages.SignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReportUtil;
import utils.TestDataUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUpTest extends BaseTest
{
    SignUpPage signUpPage;
    WebDriverWait wait;

    @BeforeClass
    public void setUpTestClass()
    {
        //Generate and store the new email and password for different test cases
        TestDataUtil.generateAndStoreCredentials("validUser");
        TestDataUtil.generateAndStoreCredentials("invalidEmailFormatUser");
        TestDataUtil.generateAndStoreCredentials("weakPasswordUser");

        //Manually set weak password
        TestDataUtil.updatePassword("weakPasswordUser","weak");

        //Manually set invalid email format
        TestDataUtil.updateEmail("invalidEmailFormatUser","invalid-email-User");

        ReportUtil.initReports();
    }

    @BeforeMethod
    public void setUp()
    {
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void testSplashScreen()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement helloEyesLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ImageView[@content-desc=\"Hello Eyes Logo\"]")));

        Assert.assertTrue(helloEyesLogo.isDisplayed(),"Home screen is not displayed");
    }

    @Test
    public void testValidSignUp()
    {
        //Read email and password from json file
        String email = TestDataUtil.getUserData("validUser").get("email").asText();
        String password = TestDataUtil.getUserData("validUser").get("password").asText();

        System.out.println("Generated Email: " + email);
        System.out.println("Generated Password: " + password);

        ReportUtil.createTest("testValidSignUp");

        //Perform the signup steps
        signUpPage.clickAccountButton();
        signUpPage.clickCreateAccountButton();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpSubmitButton();

        //Wait for the user account screen to be displayed and verify the email element
        boolean isEmailDisplayed = signUpPage.waitForEmailElement(email);
        System.out.println("Is Email Displayed: " + isEmailDisplayed);
        Assert.assertTrue(isEmailDisplayed,"User account screen should be displayed");

        //Fetch the displayed email from the user account screen
        String displayedEmail = signUpPage.getDisplayedEmail(email);
        System.out.println("Displayed Email: " + displayedEmail);
        Assert.assertEquals(displayedEmail, email,"The displayed email should match the signed-up email.");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      //  signUpPage.logoutOrReset();
    }

    @Test
    public void testInvalidEmailSignUp()
    {
        String invalidEmail = TestDataUtil.getUserData("invalidEmailFormatUser").get("email").asText();
        String password = TestDataUtil.getUserData("invalidEmailFormatUser").get("password").asText();

        ReportUtil.createTest("testInvalidEmailSignUp");

        signUpPage.clickAccountButton();
        signUpPage.clickCreateAccountButton();
        signUpPage.enterEmail(invalidEmail);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpSubmitButton();

        Assert.assertTrue(signUpPage.invalidEmailError.isDisplayed(), "Error message should be displayed for invalid email.");
    }

    @Test
    public void testWeakPasswordSignUp()
    {
        String email = TestDataUtil.getUserData("weakPasswordUser").get("email").asText();
        String password = TestDataUtil.getUserData("weakPasswordUser").get("password").asText();

        ReportUtil.createTest("testWeakPasswordSignUp");

        signUpPage.clickAccountButton();
        signUpPage.clickCreateAccountButton();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpSubmitButton();

        Assert.assertTrue(signUpPage.invalidPasswordError.isDisplayed(), "Error message should be displayed for weak password.");
    }

    @Test
    public void testDuplicateEmail()
    {
        String duplicateEmail = TestDataUtil.getUserData("duplicateEmailUser").get("email").asText();
        String password = TestDataUtil.getUserData("duplicateEmailUser").get("password").asText();

        ReportUtil.createTest("testDuplicateEmail");

        signUpPage.clickAccountButton();
        signUpPage.clickCreateAccountButton();
        signUpPage.enterEmail(duplicateEmail);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpSubmitButton();

        //Logout or reset app to initial state
        //signUpPage.logoutOrReset();

        Assert.assertTrue(signUpPage.duplicateEmailError.isDisplayed(),"Error message should be displayed for duplicate email.");
    }

}
