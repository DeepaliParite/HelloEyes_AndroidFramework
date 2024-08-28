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

public class SignInPage
{
    private AndroidDriver driver;
    WebDriverWait wait;


    public SignInPage(AndroidDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"SIGN IN\"]")
    private WebElement SignInButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"SIGN IN\"])[2]")
    private WebElement SignIpSubmitButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"SIGN IN\"])[1]")
    public WebElement signInScreenTitle;

    @AndroidFindBy(xpath = "//android.widget.Toast[@text=\"We're sorry! This email or password is incorrect.\"]")
    public WebElement incorrectPasswordToastMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid password or sign in another way.\"]")
    public WebElement emptyPasswordError;

    public void clickSignInButton()
    {
        SignInButton.click();
    }

    public void clickSignInSubmitButton()
    {
        SignIpSubmitButton.click();
    }

    public boolean isUserLoggedIn(String email)
    {
        String dynamicXPath = String.format("//android.widget.TextView[@text='%s']",email);
        WebElement emailElement = driver.findElement(By.xpath(dynamicXPath));
        return emailElement.isDisplayed();
    }

    public boolean waitForEmailElement(String email)
    {
        try {
            String dynamicXPath = String.format("//android.widget.TextView[@text='%s']", email.toLowerCase());
            System.out.println("Generated Xpath: " + dynamicXPath.toLowerCase());
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((dynamicXPath))));
            return true;
        }catch (Exception e){
            System.out.println("Exception in waitForEmailElement: " + e.getMessage());
            return false;
        }
    }

    public String getDisplayedEmail(String email)
    {
        String dynamicXPath = String.format("//android.widget.TextView[@text='%s']",email.toLowerCase());
        WebElement emailElement = driver.findElement(By.xpath(dynamicXPath));
        return emailElement.getText().toLowerCase();
    }

}
