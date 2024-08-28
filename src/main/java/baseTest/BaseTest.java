package baseTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest
{
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeSuite
    public void configureAppium() throws MalformedURLException
    {
        service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("deepaliEmulator");
        options.setChromedriverExecutable("//Users//deepaliparite//Downloads//chromedriver-mac-x64//chromedriver");
        options.setApp("/Users/deepaliparite/IdeaProjects/HelloEyes_AndroidFramework/src/test/resources/Hello Eyes.apk");
        options.setNoReset(false);

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), options);

    }

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        try {

            if (driver != null) {
                driver.quit();
            }
            configureAppium();
        } catch (Exception e) {
            System.out.println("Error during setup: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }

}
