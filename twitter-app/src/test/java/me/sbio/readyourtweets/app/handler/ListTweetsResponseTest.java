package me.sbio.readyourtweets.app.handler;

import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class ListTweetsResponseTest {

    @Test
    public void shouldSetViewCorrectly() {
        assertThat(new ListTweetsResponse().getViewName()).isEqualTo("list_tweets");
    }

    @Test
    public void shouldHaveTweetsInModel() {
        ListTweetsResponse listTweetsResponse = new ListTweetsResponse();
        assertThat(listTweetsResponse.getModel().get("tweets")).isInstanceOf(List.class);
    }

}