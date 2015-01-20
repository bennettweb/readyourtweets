package me.sbio.readyourtweets.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Hello world!
 *
 */
public class HelloPage extends WebPage {

    public HelloPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getPageTitle() {
        WebElement header = getWebDriver().findElement(By.cssSelector("h2"));
        return header.getText();
    }
}
