package me.sbio.readyourtweets.app.handler;

import org.springframework.stereotype.Component;

@Component
public class TweetHandler {

    public ListTweetsResponse readTweets() {
        return new ListTweetsResponse();
    }
}
