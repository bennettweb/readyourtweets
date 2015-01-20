package me.sbio.readyourtweets;

public class TwitterAcceptanceTestConfig {

    private static final String PROPERTY_BASE_URL = "acceptance.baseUrl";

    private static final String DEFAULT_BASE_URL = "http://localhost:8080";

    public String getAppUrl() {
        return System.getProperty(PROPERTY_BASE_URL, DEFAULT_BASE_URL);
    }
}
