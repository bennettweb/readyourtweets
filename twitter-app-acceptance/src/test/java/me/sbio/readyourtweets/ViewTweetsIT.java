package me.sbio.readyourtweets;

import com.google.common.collect.Lists;
import me.sbio.readyourtweets.commons.config.TwitterConfig;
import me.sbio.readyourtweets.commons.util.BearerTokenCreationException;
import me.sbio.readyourtweets.domain.TwitterUserFixture;
import me.sbio.readyourtweets.pages.TweetPage;
import me.sbio.readyourtweets.ui.TwitterAppNavigator;
import me.sbio.readyourtweets.ui.TwitterAppNavigatorFactory;
import me.sbio.twitterstub.TwitterStubServer;
import me.sbio.twitterstub.mappings.MappingRegistrationException;
import me.sbio.twitterstub.mappings.auth.ValidAuthenticationMapping;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static me.sbio.readyourtweets.domain.TwitterUserFixture.aTwitterUser;
import static org.fest.assertions.Assertions.assertThat;

public class ViewTweetsIT {

    private static TwitterAppNavigator twitterAppNavigator;
    private static TwitterAppPreconditionHelper twitterAppPreconditionHelper;
    private static TwitterStubServer twitterStubServer;

    @BeforeClass
    public static void setUpClass() throws BearerTokenCreationException, MappingRegistrationException {
        twitterAppNavigator = new TwitterAppNavigatorFactory().getNavigator();
        twitterStubServer = initialiseTwitterStubServer();
        twitterAppPreconditionHelper = new TwitterAppPreconditionHelper(twitterStubServer);
    }

    private static TwitterStubServer initialiseTwitterStubServer() throws BearerTokenCreationException, MappingRegistrationException {
        TwitterConfig twitterConfig = new TwitterConfig();
        TwitterStubServer twitterStubServer = new TwitterStubServer(
                twitterConfig.getPort()
        );
        twitterStubServer.start();
        twitterStubServer.registerMappings(
                new ValidAuthenticationMapping(twitterConfig.getConsumerKey(), twitterConfig.getConsumerSecret(), 1)
        );
        return twitterStubServer;
    }

    @AfterClass
    public static void tearDown() {
        twitterAppNavigator.exit();
        twitterStubServer.stop();
    }

    @Test
    public void givenAUserWithTweets_whenViewingThisUsersTweetsPage_thenISeeThisUsersTweets() throws MappingRegistrationException {
        List<String> expectedTweets = aListOfTweets();
        TwitterUserFixture twitterUserWithTweets = aTwitterUser("sbio").withTweets(expectedTweets);
        twitterAppPreconditionHelper.ensure(twitterUserWithTweets);

        TweetPage tweetPage = twitterAppNavigator.navigateToTwitterPageForUser(twitterUserWithTweets.getScreenname());
        List<String> actualTweets = tweetPage.getTweets();
        assertThat(actualTweets).containsOnly(expectedTweets.toArray());
    }


    private List<String> aListOfTweets() {
        return Lists.newArrayList("Special Tweet 1", "Special Tweet 2", "Special Tweet 3");
    }
}
