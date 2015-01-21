package me.sbio.readyourtweets.ui;

import me.sbio.readyourtweets.TwitterAcceptanceTestConfig;
import me.sbio.readyourtweets.UrlBuilder;
import me.sbio.readyourtweets.pages.HelloPage;
import me.sbio.readyourtweets.pages.TweetPage;
import org.openqa.selenium.WebDriver;

public class TwitterAppNavigator {

    private static final String HELLO_PATH = "hello";
    private final WebDriver webDriver;
    private final TwitterAcceptanceTestConfig twitterAcceptanceTestConfig;

    public TwitterAppNavigator(WebDriver webDriver, TwitterAcceptanceTestConfig twitterAcceptanceTestConfig) {
        this.webDriver = webDriver;
        this.twitterAcceptanceTestConfig = twitterAcceptanceTestConfig;
    }

    public HelloPage navigateToHelloPage() {
        String url = new UrlBuilder(twitterAcceptanceTestConfig.getAppUrl()).withPathSegment(HELLO_PATH).build();
        webDriver.get(url);
        return new HelloPage(webDriver);
    }

    public HelloPage navigateToHelloPageForName(String name) {
        String url = new UrlBuilder(twitterAcceptanceTestConfig.getAppUrl()).
                withPathSegment(HELLO_PATH).
                withParameter("name", name).build();

        webDriver.get(url);
        return new HelloPage(webDriver);
    }

    public void exit() {
        webDriver.close();
    }

    public TweetPage navigateToTwitterPageForUser(String screenname) {
        String url = new UrlBuilder(twitterAcceptanceTestConfig.getAppUrl()).withPathSegment("tweets").build();
        webDriver.get(url);
        return new TweetPage(webDriver);
    }
}
