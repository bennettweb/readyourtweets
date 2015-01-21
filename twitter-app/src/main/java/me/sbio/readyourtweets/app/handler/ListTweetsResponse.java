package me.sbio.readyourtweets.app.handler;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class ListTweetsResponse extends ModelAndView {

    private static final String VIEW_NAME = "list_tweets";
    private static final String TWEETS_ATTRIBUTE = "tweets";

    public ListTweetsResponse(List<String> tweets) {
        setViewName(VIEW_NAME);
        addObject(TWEETS_ATTRIBUTE, tweets);
    }

    @SuppressWarnings("unchecked")
    public List<String> getTweets() {
        return (List<String>) getModel().get(TWEETS_ATTRIBUTE);
    }
}
