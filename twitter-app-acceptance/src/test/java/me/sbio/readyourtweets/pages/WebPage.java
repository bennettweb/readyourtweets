package me.sbio.readyourtweets.pages;

import org.openqa.selenium.WebDriver;

public class WebPage {

    private final WebDriver webDriver;

    public WebPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
