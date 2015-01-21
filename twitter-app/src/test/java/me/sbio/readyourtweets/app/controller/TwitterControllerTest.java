package me.sbio.readyourtweets.app.controller;

import me.sbio.readyourtweets.app.TwitterAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TwitterAppConfig.class)
@WebAppConfiguration
public class TwitterControllerTest {

    private static final String VIEW_NAME = "list_tweets";
    private static final String VIEW_LOCATION = "/WEB-INF/views/list_tweets.jsp";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnCorrectResponseForViewTweetsRequest() throws Exception {
        mockMvc.perform(get("/tweets")).
                andExpect(status().isOk()).
                andExpect(view().name(VIEW_NAME)).
                andExpect(forwardedUrl(VIEW_LOCATION));
    }

}