package me.sbio.readyourtweets.ui;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public WebDriver newFirefoxWebDriver() {
        Dimension windowSize = new Dimension(1280, 960);

        try {
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            firefoxDriver.manage().window().setSize(windowSize);
            return firefoxDriver;
        } catch (WebDriverException e) {
            throw new IllegalStateException("Failed to load firefox webdriver");
        }
    }
}
