package lib;

import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AndroidDriver driver;
    private static String AppiumUrl= "http://127.0.0.1:4723/wd/hub";


    //@Before

    @Override
    protected void setUp() throws Exception {
        super.setUp();


     DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("avd","and80");
        capabilities.setCapability("adbExecTimeout", 90000);
        capabilities.setCapability("PlatformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("ensureWebviewsHavePages", true);
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","D:/soft/JavaWikiAut/apks/org.wikipedia_2022-06-01.apk");

        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
        this.driver.rotate(ScreenOrientation.PORTRAIT);
    }

    //@After
    @Override
    protected void tearDown () throws Exception{
        driver.quit();
        super.tearDown();
    }



    }

