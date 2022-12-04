package lib;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class MainPageObject {

    protected AndroidDriver driver;
    public MainPageObject(AndroidDriver driver){
        this.driver = driver;
    }



    public WebElement webElementSearchById(String elementName) {

        WebElement element = driver.findElement(By.id(elementName));
        return element;
    }

    public void clickWebElement (WebElement element) {

        element.click();
    }

    public WebElement searchAndWaitById(String elementName, long timeout){

        WebElement element =
                new WebDriverWait(driver, Duration.ofMillis(timeout))
                        .until(ExpectedConditions.presenceOfElementLocated(By.id(elementName)));
        return element;
    }

    public WebElement searchAndWaitByXpath (String elementName, long timeout){

        WebElement element =
                new WebDriverWait(driver, Duration.ofMillis(timeout))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementName)));
        return element;
    }

    public void clickAndSendKeys (WebElement element, String keys) {
        element.click();
        element.sendKeys(keys);
    }


    public static void assertElement (WebElement element, String expected){
        String actual = element.getAttribute ("text");
        Assert.assertEquals(
                "wrong header",
                expected,
                actual
        );
    }

    public void scrollDown (WebElement element, long scrollTime) {

        WebElement scrollDown = element;

        int centerX = scrollDown.getRect().x + (scrollDown.getSize().width/2);
        double startY = scrollDown.getRect().y + (scrollDown.getSize().height * 0.8);
        double endY = scrollDown.getRect().y + (scrollDown.getSize().height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence scroll = new Sequence(finger, 1);
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, (int)startY));
        scroll.addAction(finger.createPointerDown(0));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(scrollTime), PointerInput.Origin.viewport(),centerX,(int)endY));
        scroll.addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(scroll));
      }



    public void rotateBothSidesAndCompare (String elementName, long timeout) {             // side = LANDSCAPE OR PORTRAIT

        WebElement element1 =
                new WebDriverWait(driver, Duration.ofMillis(timeout))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementName)));

        String articleBeforeRotate = element1.getAttribute("text");

        driver.rotate(ScreenOrientation.LANDSCAPE);

        WebElement element2 =
                new WebDriverWait(driver, Duration.ofMillis(timeout))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementName)));

        String articleAfterRotate = element2.getAttribute("text");
        Assert.assertEquals(
                "cant find element",
                articleBeforeRotate,
                articleAfterRotate);

        driver.rotate(ScreenOrientation.PORTRAIT);

        WebElement element3 =
                new WebDriverWait(driver, Duration.ofMillis(timeout))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementName)));

        String articleAfterSecondRotate = element3.getAttribute("text");
        Assert.assertEquals(
                "cant find element",
                articleAfterRotate,
                articleAfterSecondRotate);
    }


    public void jumpToBackgroundAndCompare (String elementName, long timeout) {
        WebElement element =
                new WebDriverWait(driver, Duration.ofMillis(timeout))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementName)));

        String articleBeforeMinimize = element.getAttribute("text");

        driver.runAppInBackground(Duration.ofMillis(2000));

        String articleAfterMinimize = element.getAttribute("text");
        Assert.assertEquals(
                "wrong header",
                articleBeforeMinimize,
                articleAfterMinimize);
    }
}
