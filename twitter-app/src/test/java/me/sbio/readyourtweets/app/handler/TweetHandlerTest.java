package me.sbio.readyourtweets.app.handler;

import com.google.common.collect.Lists;
import me.sbio.readyourtweets.twitterapiclient.TwitterApi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetHandlerTest {

    private TweetHandler tweetHandler;

    @Mock
    private TwitterApi mockTwitterApi;

    @Before
    public void setUp() {
        tweetHandler = new TweetHandler(mockTwitterApi);
    }

    @Test
    public void shouldReturnTweetsResponse() {
        List<String> tweets = Lists.newArrayList("Tweet 1", "Tweet 2", "Tweet 3");
        when(mockTwitterApi.userTimeline(anyString())).thenReturn(tweets);

        ListTweetsResponse listTweetsResponse = tweetHandler.readTweets();
        assertThat(listTweetsResponse.getTweets()).containsOnly(tweets.toArray());
    }
}