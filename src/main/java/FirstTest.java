import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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

        options.setAppActivity("com.wiseasy.cashier.Sale"); // Replace with the actual Purchase activity name
        options.setNoReset(true);

        /*options.setAppActivity("com.wiseasy.cashier.Transactions"); // Replace with the actual Purchase activity name
        options.setNoReset(true);*/

        try {
            driver = new AndroidDriver(new URL(appiumServerUrl), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test() {
        //driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign in\")")).click();.

        // 1. Create a wait object (10 seconds max)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        //login Test
       /* wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign in\")")
        )).click();*/

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        //Purchase Test
      /*  wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)")
        )).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(12)")
        )).click();*/

       /* wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Purchase\")")
        )).click();*/

    }

    /*@AfterTest
    public void close() {

        driver.quit();

    }*/
}


