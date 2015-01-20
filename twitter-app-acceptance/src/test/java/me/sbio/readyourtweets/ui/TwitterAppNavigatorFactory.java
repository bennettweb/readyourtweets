package me.sbio.readyourtweets.ui;

import me.sbio.readyourtweets.TwitterAcceptanceTestConfig;
import org.openqa.selenium.WebDriver;

public class TwitterAppNavigatorFactory {

    private final TwitterAcceptanceTestConfig twitterAcceptanceTestConfig;

    public TwitterAppNavigatorFactory() {
        this.twitterAcceptanceTestConfig = new TwitterAcceptanceTestConfig();
    }

    public TwitterAppNavigator getNavigator() {
        WebDriver webDriver = new WebDriverFactory().newFirefoxWebDriver();
        return new TwitterAppNavigator(webDriver, twitterAcceptanceTestConfig);
    }

}
