package me.sbio.readyourtweets.twitterapiclient.util;

import me.sbio.readyourtweets.commons.config.TwitterConfig;

public class FakeTwitterConfig extends TwitterConfig {

    private static final String CONSUMER_KEY = "my_consumer_key";
    private static final String CONSUMER_SECRET = "my_consumer_secret";
    private static final String BASE_URI = "http://localhost";
    private static final int PORT = 8080;
    private static final String BASE_PATH = "api";

    @Override
    public String getConsumerKey() {
        return CONSUMER_KEY;
    }

    @Override
    public String getConsumerSecret() {
        return CONSUMER_SECRET;
    }

    @Override
    public String getBaseUri() {
        return BASE_URI;
    }

    @Override
    public int getPort() {
        return PORT;
    }

    @Override
    public String getBasePath() {
        return BASE_PATH;
    }
}
