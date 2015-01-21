package me.sbio.readyourtweets.twitterapiclient;

import com.google.common.collect.Lists;
import me.sbio.readyourtweets.twitterapiclient.config.TwitterConfig;

import java.util.List;

public class TwitterApi {

    private final TwitterConfig twitterConfig;

    public TwitterApi() {
        this.twitterConfig = new TwitterConfig();
    }

    public TwitterApi authenticate() {
        return this;
    }

    public List<String> userTimeline(String user) {
        return Lists.newLinkedList();
    }

}
