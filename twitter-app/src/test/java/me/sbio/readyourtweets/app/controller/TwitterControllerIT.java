package me.sbio.readyourtweets.app.controller;

import com.google.common.collect.Lists;
import me.sbio.readyourtweets.app.StubTwitterAppConfig;
import me.sbio.readyourtweets.app.TwitterAppConfig;
import me.sbio.readyourtweets.commons.config.StubTwitterConfig;
import me.sbio.readyourtweets.commons.config.TwitterConfig;
import me.sbio.readyourtweets.commons.util.BearerTokenCreationException;
import me.sbio.twitterstub.TwitterStubServer;
import me.sbio.twitterstub.mappings.MappingRegistrationException;
import me.sbio.twitterstub.mappings.timeline.UserTimelineMapping;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static me.sbio.twitterstub.TwitterStubServer.authenticationMappings;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TwitterAppConfig.class, StubTwitterAppConfig.class})
@WebAppConfiguration
public class TwitterControllerIT {

    private static final String VIEW_NAME = "list_tweets";
    private static final String VIEW_LOCATION = "/WEB-INF/views/list_tweets.jsp";

    private static final TwitterConfig TWITTER_CONFIG = new StubTwitterConfig();
    private static TwitterStubServer TWITTER_STUB_SERVER;

    private MockMvc mockMvc;

    @BeforeClass
    public static void setUpStubServer() throws BearerTokenCreationException, MappingRegistrationException {
        TWITTER_STUB_SERVER = new TwitterStubServer(TWITTER_CONFIG.getPort());
        TWITTER_STUB_SERVER.start();
        TWITTER_STUB_SERVER.registerMappings(
                authenticationMappings(TWITTER_CONFIG.getConsumerKey(), TWITTER_CONFIG.getConsumerSecret())
        );
    }

    @AfterClass
    public static void stopStubServer() {
        TWITTER_STUB_SERVER.stop();
    }

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnCorrectResponseForViewTweetsRequest() throws Exception {
        List<String> tweets = Lists.newLinkedList();
        TWITTER_STUB_SERVER.registerMappings(new UserTimelineMapping("sb_io", tweets));

        mockMvc.perform(get("/tweets?user=sb_io")).
                andExpect(status().isOk()).
                andExpect(model().attribute("tweets", tweets)).
                andExpect(view().name(VIEW_NAME)).
                andExpect(forwardedUrl(VIEW_LOCATION));
    }

}