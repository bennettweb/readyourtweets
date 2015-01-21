package me.sbio.readyourtweets.domain;

import java.util.List;

public class TwitterUserFixture {

    private String screenname;
    private List<String> tweets;

    public TwitterUserFixture withTweets(List<String> tweets) {
        this.tweets = tweets;
        return this;
    }

    public TwitterUserFixture withScreenname(String screenname) {
        this.screenname = screenname;
        return this;
    }

    public String getScreenname() {
        return screenname;
    }

    public List<String> getTweets() {
        return tweets;
    }
}
