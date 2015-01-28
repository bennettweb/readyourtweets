package me.sbio.readyourtweets.app.handler;

import me.sbio.readyourtweets.commons.util.BearerTokenCreationException;
import me.sbio.readyourtweets.twitterapiclient.TwitterApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TweetHandler {

    private final TwitterApi twitterApi;

    @Autowired
    public TweetHandler(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    public ListTweetsResponse readTweets(String username) throws BearerTokenCreationException {
        twitterApi.authenticate();
        List<String> tweets = twitterApi.userTimeline(username);
        return new ListTweetsResponse(tweets);
    }
}
