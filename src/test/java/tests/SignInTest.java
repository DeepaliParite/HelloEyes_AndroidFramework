package tests;

import baseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignInPage;
import pages.SignUpPage;
import utils.ReportUtil;
import utils.TestDataUtil;

import java.time.Duration;

public class SignInTest extends BaseTest
{
    WebDriverWait wait;
    SignUpPage signUpPage;
    SignInPage signInPage;

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
        signInPage = new SignInPage(driver);
    }

    @Test
    public void testValidSignIn()
    {
        String email = TestDataUtil.getUserData("validUserSignIn").get("email").asText();
        String password = TestDataUtil.getUserData("validUserSignIn").get("password").asText();

        ReportUtil.createTest("testValidSignIn");

        signUpPage.clickAccountButton();
        signInPage.clickSignInButton();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signInPage.clickSignInSubmitButton();


        boolean isEmailDisplayed = signUpPage.waitForEmailElement(email);
        System.out.println("Is Email Displayed: " + isEmailDisplayed);
        Assert.assertTrue(isEmailDisplayed,"User account screen should be displayed");

        //Fetch the displayed email from the user account screen
        String displayedEmail = signUpPage.getDisplayedEmail(email);
        System.out.println("Displayed Email: " + displayedEmail);
        Assert.assertEquals(displayedEmail, email,"The displayed email should match the signed-up email.");
    }

    @Test
    public void testInvalidEmailFormat()
    {
        String invalidEmail = TestDataUtil.getUserData("invalidEmailFormatUser").get("email").asText();
        String password = TestDataUtil.getUserData("invalidEmailFormatUser").get("password").asText();

        ReportUtil.createTest("testInvalidEmailSignIn");

        signUpPage.clickAccountButton();
        signInPage.clickSignInButton();
        signUpPage.enterEmail(invalidEmail);
        signUpPage.enterPassword(password);
        signInPage.clickSignInSubmitButton();

        Assert.assertTrue(signUpPage.invalidEmailError.isDisplayed(), "Error message should be displayed for invalid email.");
    }

    @Test
    public void testIncorrectPassword()
    {
        String email = TestDataUtil.getUserData("incorrectPasswordUser").get("email").asText();
        String password = TestDataUtil.getUserData("incorrectPasswordUser").get("password").asText();

        ReportUtil.createTest("testIncorrectPassword");

        signUpPage.clickAccountButton();
        signInPage.clickSignInButton();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signInPage.clickSignInSubmitButton();

        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("text");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertEquals(toastMessage,"We're sorry! This email or password is incorrect.");
    }

    @Test
    public void testEmptyEmail()
    {
        String email = TestDataUtil.getUserData("testEmptyEmail").get("email").asText();
        String password = TestDataUtil.getUserData("testEmptyEmail").get("password").asText();

        ReportUtil.createTest("testEmptyEmail");

        signUpPage.clickAccountButton();
        signInPage.clickSignInButton();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signInPage.clickSignInSubmitButton();

        Assert.assertTrue(signUpPage.invalidEmailError.isDisplayed(), "Error message should be displayed for empty email field.");
    }

    @Test
    public void testEmptyPassword()
    {
        String email = TestDataUtil.getUserData("testEmptyPassword").get("email").asText();
        String password = TestDataUtil.getUserData("testEmptyPassword").get("password").asText();

        ReportUtil.createTest("testEmptyPassword");

        signUpPage.clickAccountButton();
        signInPage.clickSignInButton();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signInPage.clickSignInSubmitButton();

        Assert.assertTrue(signInPage.emptyPasswordError.isDisplayed(), "Error message should be displayed for empty password field.");
    }

    @Test
    public void testEmptyEmailAndPassword()
    {
        String email = TestDataUtil.getUserData("testEmptyEmailAndPassword").get("email").asText();
        String password = TestDataUtil.getUserData("testEmptyEmailAndPassword").get("password").asText();

        ReportUtil.createTest("testEmptyEmailAndPassword");

        signUpPage.clickAccountButton();
        signInPage.clickSignInButton();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signInPage.clickSignInSubmitButton();

        Assert.assertTrue(signUpPage.invalidEmailError.isDisplayed(), "Error message should be displayed for empty email and password field.");
    }

    @Test
    public void testUnregisteredEmail()
    {
        String email = TestDataUtil.getUserData("unregisteredEmail").get("email").asText();
        String password = TestDataUtil.getUserData("unregisteredEmail").get("password").asText();

        ReportUtil.createTest("testUnregisteredEmail");

        signUpPage.clickAccountButton();
        signInPage.clickSignInButton();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signInPage.clickSignInSubmitButton();

        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("text");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertEquals(toastMessage,"We're sorry! This email or password is incorrect.");
    }

    @Test
    public void testCaseSensitivityInEmail()
    {
        String email = TestDataUtil.getUserData("caseSensitivityInEmail").get("email").asText();
        String password = TestDataUtil.getUserData("caseSensitivityInEmail").get("password").asText();

        ReportUtil.createTest("testCaseSensitivityInEmail");

        signUpPage.clickAccountButton();
        signInPage.clickSignInButton();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signInPage.clickSignInSubmitButton();

        boolean isEmailDisplayed = signInPage.waitForEmailElement(email);
        System.out.println("Is Email Displayed: " + isEmailDisplayed);
        Assert.assertTrue(isEmailDisplayed,"User account screen should be displayed");

        //Fetch the displayed email from the user account screen
        String displayedEmail = signInPage.getDisplayedEmail(email.toLowerCase());
        System.out.println("Displayed Email: " + displayedEmail);
        Assert.assertEquals(displayedEmail.toLowerCase(), email.toLowerCase(),"The displayed email should match the signed-up email.");

    }


}
