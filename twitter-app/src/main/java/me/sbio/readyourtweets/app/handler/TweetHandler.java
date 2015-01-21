package me.sbio.readyourtweets.app.handler;

import me.sbio.readyourtweets.twitterapiclient.TwitterApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TweetHandler {

    private static final String DEFAULT_USER = "sbio";
    private final TwitterApi twitterApi;

    @Autowired
    public TweetHandler(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    public ListTweetsResponse readTweets() {
        List<String> tweets = twitterApi.userTimeline(DEFAULT_USER);
        return new ListTweetsResponse(tweets);
    }
}
