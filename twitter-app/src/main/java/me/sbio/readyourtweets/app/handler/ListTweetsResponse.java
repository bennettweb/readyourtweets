package me.sbio.readyourtweets.app.handler;

import com.google.common.collect.Lists;
import org.springframework.web.servlet.ModelAndView;

public class ListTweetsResponse extends ModelAndView {

    private static final String VIEW_NAME = "list_tweets";
    private static final String TWEETS_ATTRIBUTE = "tweets";

    public ListTweetsResponse() {
        setViewName(VIEW_NAME);
        addObject(TWEETS_ATTRIBUTE, Lists.newArrayList());
    }

}
