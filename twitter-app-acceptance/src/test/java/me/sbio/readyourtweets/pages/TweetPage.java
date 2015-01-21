package me.sbio.readyourtweets.pages;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TweetPage extends WebPage {

    public TweetPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getTweets() {
        List<WebElement> tweetElements = getWebDriver().findElements(By.cssSelector("ul.tweets li"));
        List<String> tweetTexts = Lists.newArrayList();
        for (WebElement tweetElem : tweetElements) {
            tweetTexts.add(tweetElem.getText());
        }
        return tweetTexts;
    }
}
