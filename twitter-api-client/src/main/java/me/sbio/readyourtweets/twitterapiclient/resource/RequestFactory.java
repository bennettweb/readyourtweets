package me.sbio.readyourtweets.twitterapiclient.resource;

import me.sbio.readyourtweets.commons.config.TwitterConfig;
import me.sbio.readyourtweets.commons.util.BearerTokenCreationException;
import me.sbio.readyourtweets.commons.util.TwitterKeyUtil;
import me.sbio.readyourtweets.twitterapiclient.resource.auth.AuthenticationRequest;
import me.sbio.readyourtweets.twitterapiclient.resource.timeline.UserTimelineRequest;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


public class RequestFactory {

    private final TwitterConfig twitterConfig;
    private final TwitterKeyUtil twitterKeyUtil;
    private final EntityFactory entityFactory;
    private final Client client;
    private WebTarget baseWebTarget;

    public RequestFactory(TwitterConfig twitterConfig, TwitterKeyUtil twitterKeyUtil, EntityFactory entityFactory) {
        this.twitterConfig = twitterConfig;
        this.twitterKeyUtil = twitterKeyUtil;
        this.entityFactory = entityFactory;
        ClientConfig clientConfig = new ClientConfig();
        client = ClientBuilder.newClient(clientConfig);
        client.register(JacksonFeature.class);
        baseWebTarget = client.target(twitterConfig.getBaseUri() + ":" + twitterConfig.getPort());
    }

    public RequestFactory(TwitterConfig twitterConfig) {
        this(twitterConfig, new TwitterKeyUtil(), new EntityFactory());
    }

    public AuthenticationRequest authenticateRequest() throws BearerTokenCreationException {
        String encodedBearerToken = twitterKeyUtil.createEncodedBearerToken(
                twitterConfig.getConsumerKey(),
                twitterConfig.getConsumerSecret()
        );
        return new AuthenticationRequest(baseWebTarget, entityFactory, encodedBearerToken);
    }

    public UserTimelineRequest userTimelineRequest(String accessToken, String user) {
        return new UserTimelineRequest(baseWebTarget, accessToken, user);
    }
}
