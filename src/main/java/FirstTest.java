import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FirstTest {

    public AndroidDriver driver;

    @BeforeTest
    public void setup() {

        //Server Url
        String appiumServerUrl = "http://127.0.0.1:4723";// or http://127.0.0.1:4723/  (both usually work)
        // Modern replacement
        UiAutomator2Options options = new UiAutomator2Options();
        String appPath = System.getProperty("user.dir") + "/.idea/apps/Cashier.apk";

        options.setPlatformName("ANDROID");
        options.setAutomationName("UiAutomator2");// "uiautomator2" also works (case-insensitive)
        options.setApp(appPath);//("user.dir") + "/apps/ApiDemos.apk");

        try {
            driver = new AndroidDriver(new URL(appiumServerUrl), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test() {
        //driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign in\")")).click();.

        // 1. Create a wait object (50 seconds max)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

        // 1. Click Sign In
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign in\")")
        )).click();

        // 2. Fast-click to the Purchase screen
        // (Using the selector you found in the Inspector)
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(9)")
        )).click();

        // 3. Press 1
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)")
        )).click();

        // 4. Press Pay
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(12)")
        )).click();

        // Differentiates by finding 'OK' inside the same container as the header
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"OK\")")
        )).click();

    }

    @AfterTest
    public void close(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            captureScreenshot(result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }

    public void captureScreenshot(String testName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // This line ensures the folder exists
        File targetLayer = new File("screenshots");
        if (!targetLayer.exists()) {
            targetLayer.mkdir();
        }

        try {
            FileUtils.copyFile(scrFile, new File("screenshots/" + testName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


