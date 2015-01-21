package me.sbio.readyourtweets;

import me.sbio.readyourtweets.twitterapiclient.config.TwitterConfig;
import me.sbio.twitterstub.TwitterStubServer;

public class TwitterStubServerWrapper {

    private static final TwitterStubServerWrapper INSTANCE = new TwitterStubServerWrapper();
    private static final TwitterConfig TWITTER_CONFIG = new TwitterConfig();

    private final TwitterStubServer twitterStubServer;

    private TwitterStubServerWrapper() {
        this.twitterStubServer = new TwitterStubServer(
                TWITTER_CONFIG.getPort(),
                TWITTER_CONFIG.getConsumerKey(),
                TWITTER_CONFIG.getConsumerSecret()
        );
    }

    public static final TwitterStubServer getTwitterStubServer() {
        return INSTANCE.twitterStubServer;
    }
}
