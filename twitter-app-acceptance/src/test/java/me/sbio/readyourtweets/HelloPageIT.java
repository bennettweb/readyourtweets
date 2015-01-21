package me.sbio.readyourtweets;

import me.sbio.readyourtweets.pages.HelloPage;
import me.sbio.readyourtweets.ui.TwitterAppNavigator;
import me.sbio.readyourtweets.ui.TwitterAppNavigatorFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class HelloPageIT {

    private static TwitterAppNavigator twitterAppNavigator;

    @BeforeClass
    public static void setUpClass() {
        twitterAppNavigator = new TwitterAppNavigatorFactory().getNavigator();
    }

    @AfterClass
    public static void tearDown() {
        twitterAppNavigator.exit();
    }

    @Test
    public void shouldHaveCorrectTitleForRequestWithoutName() {
        HelloPage helloPage = twitterAppNavigator.navigateToHelloPage();
        assertThat(helloPage.getPageTitle()).isEqualTo("Hello !");
    }

    @Test
    public void shouldDisplayNameForRequestWithNameParameter() {
        HelloPage helloPage = twitterAppNavigator.navigateToHelloPageForName("sbio");
        assertThat(helloPage.getPageTitle()).isEqualTo("Hello sbio!");
    }

}
