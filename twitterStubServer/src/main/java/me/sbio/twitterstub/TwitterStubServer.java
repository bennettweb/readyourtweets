package me.sbio.twitterstub;

import com.github.tomakehurst.wiremock.WireMockServer;
import me.sbio.twitterstub.mappings.auth.AuthenticationMapping;
import me.sbio.twitterstub.mappings.MappingRegistrationException;
import me.sbio.twitterstub.mappings.timeline.UserTimelineMapping;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class TwitterStubServer {

    private final WireMockServer wireMockServer;
    private int port;

    public TwitterStubServer(int port) {
        this.port = port;
        wireMockServer = new WireMockServer(wireMockConfig().port(port));
    }

    public void start() {
        wireMockServer.start();
    }

    public void registerMappings() throws MappingRegistrationException {
        configureFor("localhost", port);
        new AuthenticationMapping().register();
        new UserTimelineMapping().register();
    }

    public void stop() {
        wireMockServer.stop();
    }

    public static void main(String[] args) throws MappingRegistrationException {
        TwitterStubServer twitterStubServer = new TwitterStubServer(8080);
        twitterStubServer.start();

        twitterStubServer.registerMappings();
    }
}
