package me.sbio.readyourtweets.app.controller;

import me.sbio.readyourtweets.app.handler.TweetHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TwitterController {

    @Autowired
    private TweetHandler tweetHandler;

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    public ModelAndView showTweets() {
        return tweetHandler.readTweets();
    }
}
