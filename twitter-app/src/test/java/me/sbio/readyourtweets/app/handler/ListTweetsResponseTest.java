package me.sbio.readyourtweets.app.handler;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class ListTweetsResponseTest {

    private static final List<String> TWEETS = Lists.newArrayList("Tweet 1", "Tweet 2", "Tweet 3");

    @Test
    public void shouldSetViewCorrectly() {
        assertThat(new ListTweetsResponse(TWEETS).getViewName()).isEqualTo("list_tweets");
    }

    @Test
    public void shouldHaveTweetsInModel() {
        ListTweetsResponse listTweetsResponse = new ListTweetsResponse(TWEETS);
        assertThat((List) listTweetsResponse.getModel().get("tweets")).containsOnly(TWEETS.toArray());
    }

    @Test
    public void shouldReturnTweets() {
        ListTweetsResponse listTweetsResponse = new ListTweetsResponse(TWEETS);
        assertThat(listTweetsResponse.getTweets()).containsOnly(TWEETS.toArray());
    }

}