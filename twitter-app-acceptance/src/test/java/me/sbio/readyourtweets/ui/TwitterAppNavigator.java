package me.sbio.readyourtweets.ui;

import me.sbio.readyourtweets.TwitterAcceptanceTestConfig;
import me.sbio.readyourtweets.UrlBuilder;
import me.sbio.readyourtweets.pages.TweetPage;
import org.openqa.selenium.WebDriver;

public class TwitterAppNavigator {

    private final WebDriver webDriver;
    private final TwitterAcceptanceTestConfig twitterAcceptanceTestConfig;

    public TwitterAppNavigator(WebDriver webDriver, TwitterAcceptanceTestConfig twitterAcceptanceTestConfig) {
        this.webDriver = webDriver;
        this.twitterAcceptanceTestConfig = twitterAcceptanceTestConfig;
    }

    public void exit() {
        webDriver.close();
    }

    public TweetPage navigateToTwitterPageForUser(String screenname) {
        String url = new UrlBuilder(twitterAcceptanceTestConfig.getAppUrl()).
                withPathSegment("tweets").
                withParameter("user", screenname).
                build();
        webDriver.get(url);
        return new TweetPage(webDriver);
    }
}
