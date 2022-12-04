import lib.CoreTestCase;
import lib.MainPageObject;
import org.junit.Test;


public class testCases extends CoreTestCase {

    private MainPageObject MainPageObject;
    protected void setUp() throws Exception{
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }



    @Test
    public void testArticle () throws InterruptedException {
        MainPageObject.clickWebElement(MainPageObject.webElementSearchById("fragment_onboarding_skip_button"));
        MainPageObject.clickWebElement(MainPageObject.searchAndWaitById("org.wikipedia:id/search_container", 5000));
        MainPageObject.clickAndSendKeys(MainPageObject.searchAndWaitById("org.wikipedia:id/search_src_text", 5000),"Selenium");
        MainPageObject.clickWebElement(MainPageObject.searchAndWaitByXpath("//*[@text = 'Selenium (software)']",5000));
        MainPageObject.assertElement(MainPageObject.searchAndWaitByXpath("//android.widget.TextView[@resource-id ='pcs-edit-section-title-description']", 10000),
                ("Testing framework for web applications"));
    }

   @Test
    public void testScroll() throws InterruptedException {
       MainPageObject.clickWebElement(MainPageObject.webElementSearchById("fragment_onboarding_skip_button"));
       MainPageObject.scrollDown(MainPageObject.searchAndWaitById("org.wikipedia:id/feed_view", 5000), 2000);
       MainPageObject.assertElement(MainPageObject.searchAndWaitById("org.wikipedia:id/view_card_header_title",5000),
                ("Featured article"));
    }


   @Test
    public void testRotation() throws InterruptedException {

        MainPageObject.clickWebElement(MainPageObject.webElementSearchById("fragment_onboarding_skip_button"));
        MainPageObject.clickWebElement(MainPageObject.searchAndWaitById("org.wikipedia:id/search_container", 5000));
        MainPageObject.clickAndSendKeys(MainPageObject.searchAndWaitById("org.wikipedia:id/search_src_text", 5000), "Html");
        MainPageObject.rotateBothSidesAndCompare("//*[@text = 'Hypertext Markup Language']", 5000);
    }

    @Test public void testJumpToBackgroundAndBack() {

        MainPageObject.clickWebElement(MainPageObject.webElementSearchById("fragment_onboarding_skip_button"));
        MainPageObject.clickWebElement(MainPageObject.searchAndWaitById("org.wikipedia:id/search_container", 5000));
        MainPageObject.clickAndSendKeys(MainPageObject.searchAndWaitById("org.wikipedia:id/search_src_text", 5000), "Html");
        MainPageObject.jumpToBackgroundAndCompare("//*[@text = 'Hypertext Markup Language']",5000);
    }





    }










