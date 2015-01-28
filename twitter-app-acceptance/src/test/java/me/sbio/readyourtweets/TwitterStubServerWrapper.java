package me.sbio.readyourtweets;

import me.sbio.readyourtweets.commons.config.TwitterConfig;
import me.sbio.twitterstub.TwitterStubServer;

public class TwitterStubServerWrapper {

    private static final TwitterStubServerWrapper INSTANCE = new TwitterStubServerWrapper();
    private static final TwitterConfig TWITTER_CONFIG = new TwitterConfig();

    private final TwitterStubServer twitterStubServer;

    private TwitterStubServerWrapper() {
        this.twitterStubServer = new TwitterStubServer(
                TWITTER_CONFIG.getPort()
        );
    }

    public static final TwitterStubServer getTwitterStubServer() {
        return INSTANCE.twitterStubServer;
    }
}
