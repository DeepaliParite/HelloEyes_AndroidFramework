package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage
{
    private AndroidDriver driver;
    WebDriverWait wait;

    public SignUpPage(AndroidDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Account\"]")
    private WebElement accountButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"CREATE ACCOUNT\"]")
    private WebElement CreateAccountButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Email address\"]")
    private WebElement clickEmailField;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[1]")
    private WebElement emailField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Password\"]")
    private WebElement clickPasswordField;
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[2]")
    private WebElement passwordField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"CREATE ACCOUNT\"]")
    private WebElement SignUpSubmitButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"leuttetatuque-841@yopmail.com\"]")
    public WebElement userEmailDisplayed;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid email address.\"]")
    public WebElement invalidEmailError;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please create a password that matches the requirements.\"]")
    public WebElement invalidPasswordError;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"There's already an account with that email address. Sign in or use a different email address.\"]")
    public WebElement duplicateEmailError;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Log out\"]")
    public WebElement logoutButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Log out\"])[2]")
    public WebElement logoutButtonInAlert;

    public void clickAccountButton()
    {
        accountButton.click();
    }
    public void clickCreateAccountButton(){CreateAccountButton.click();}

    public void enterEmail(String email)
    {
        clickEmailField.click();
        emailField.sendKeys(email);
    }
    public void enterPassword(String password)
    {
        clickPasswordField.click();
        passwordField.sendKeys(password);
    }

    public void clickSignUpSubmitButton(){SignUpSubmitButton.click();}

    public boolean waitForEmailElement(String email)
    {
        try {
            String dynamicXPath = String.format("//android.widget.TextView[@text='%s']", email);
            System.out.println("Generated Xpath: " + dynamicXPath);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((dynamicXPath))));
            return true;
        }catch (Exception e){
            System.out.println("Exception in waitForEmailElement: " + e.getMessage());
            return false;
        }
    }

    public String getDisplayedEmail(String email)
    {
        String dynamicXPath = String.format("//android.widget.TextView[@text='%s']",email);
        WebElement emailElement = driver.findElement(By.xpath(dynamicXPath));
        return emailElement.getText();
    }

    public void logoutOrReset()
    {
        if(logoutButton.isDisplayed())
        {
            logoutButton.click();
            logoutButtonInAlert.click();
        }
    }

}
