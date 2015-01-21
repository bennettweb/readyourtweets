package me.sbio.twitterstub;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import me.sbio.readyourtweets.twitterapiclient.config.TwitterConfig;
import me.sbio.readyourtweets.twitterapiclient.util.BearerTokenCreationException;
import me.sbio.twitterstub.mappings.Mapping;
import me.sbio.twitterstub.mappings.MappingRegistrationException;
import me.sbio.twitterstub.mappings.auth.MatchAllAuthenticationMapping;
import me.sbio.twitterstub.mappings.auth.ValidAuthenticationMapping;
import me.sbio.twitterstub.mappings.timeline.DefaultUserTimelineMapping;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class TwitterStubServer {

    private final WireMockServer wireMockServer;
    private final String consumerKey;
    private final String consumerSecret;
    private final int port;

    public TwitterStubServer(int port, String consumerKey, String consumerSecret) {
        this.port = port;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        wireMockServer = new WireMockServer(wireMockConfig().port(port));
    }

    public void start() {
        wireMockServer.start();
    }

    public void registerMappings(Mapping... mappings) throws MappingRegistrationException {
        configureFor("localhost", port);
        reset();
        for (int i=0; i<mappings.length; i++) {
            stubFor(mappings[i].request().atPriority(i+1).willReturn(mappings[i].response()));
        }
    }

    public void stop() {
        wireMockServer.stop();
    }

    public void reset() {
        WireMock.reset();
    }

    public boolean isRunning() {
        return wireMockServer.isRunning();
    }

    public void registerDefaultMappings() {
        try {
            registerMappings(defaultMappings());
        } catch (MappingRegistrationException | BearerTokenCreationException e) {
            throw new IllegalStateException("Failed to register default mappings", e);
        }
    }

    public static void main(String[] args) throws MappingRegistrationException, BearerTokenCreationException {
        TwitterConfig twitterConfig = new TwitterConfig();

        TwitterStubServer twitterStubServer = new TwitterStubServer(twitterConfig.getPort(), twitterConfig.getConsumerKey(), twitterConfig.getConsumerSecret());
        twitterStubServer.start();

        twitterStubServer.registerDefaultMappings();
    }

    private Mapping[] defaultMappings() throws BearerTokenCreationException {
        return new Mapping[] {
                new ValidAuthenticationMapping(consumerKey, consumerSecret, 1),
                new MatchAllAuthenticationMapping(),
                new DefaultUserTimelineMapping()
        };
    }
}
