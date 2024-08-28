package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportUtil
{
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReports() {
        if (extent == null)
        {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Functional Testing");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Host Name", "MyHost");
            extent.setSystemInfo("Project Name", "Hello Eyes");
            extent.setSystemInfo("Tester", "Deepali");

        }
    }

    public static ExtentReports getExtentReports()
    {
        if(extent == null)
        {
            initReports();
        }
        return extent;
    }

    public static ExtentTest createTest(String testName)
    {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
       // test = extent.createTest(testName);
        System.out.println("Created ExtentTest for: " + testName);
        return extentTest;
    }

    public static void flushReports()
    {
        if (extent != null)
        {
            extent.flush();
        }
    }

    public static String getScreenshot(AppiumDriver driver, String screenshotName) throws IOException
    {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/test-output/FailedTestsScreenshots/" +screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

}
