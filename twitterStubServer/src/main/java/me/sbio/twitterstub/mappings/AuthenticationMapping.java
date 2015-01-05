package me.sbio.twitterstub.mappings;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import me.sbio.readyourtweets.twitterapiclient.config.TwitterConfig;
import me.sbio.readyourtweets.twitterapiclient.util.BearerTokenCreationException;
import me.sbio.readyourtweets.twitterapiclient.util.TwitterKeyUtil;
import me.sbio.twitterstub.TwitterStubServerParams;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static me.sbio.twitterstub.TwitterStubServerParams.*;

public class AuthenticationMapping implements Mapping {

    private final TwitterConfig twitterConfig;
    private final TwitterKeyUtil twitterKeyUtil;

    public AuthenticationMapping() {
        twitterConfig = new TwitterConfig();
        twitterKeyUtil = new TwitterKeyUtil();
    }

    @Override
    public void register() throws MappingRegistrationException {
        registerValidAuthenticationMapping();
        registerInvalidAuthenticationMapping();
    }

    private void registerInvalidAuthenticationMapping() {
        stubFor(anInvalidAuthenticationRequest().atPriority(2).willReturn(anInvalidAuthenticationResponse()));
    }


    private void registerValidAuthenticationMapping() throws MappingRegistrationException {
        try {
            stubFor(aValidAuthenticationRequest().atPriority(1).willReturn(aValidAuthenticationResponse()));
        } catch (BearerTokenCreationException e) {
            throw new MappingRegistrationException("Failed to register mapping for valid twitter auth endpoint", e);
        }
    }

    private MappingBuilder aValidAuthenticationRequest() throws BearerTokenCreationException {
        return anAuthenticationRequest().
                withHeader("Authorization", matching("Basic " + anEncodedBearerToken()));
    }

    private ResponseDefinitionBuilder aValidAuthenticationResponse() {
        return aResponse().
                withStatus(200).
                withHeader("Content-Type", "application/json; charset=UTF-8").
                withBody("{\"token_type\":\"bearer\",\"access_token\":\"" + ACCESS_TOKEN + "\"}");

    }

    private MappingBuilder anInvalidAuthenticationRequest() {
        return anAuthenticationRequest().
                withHeader("Authorization", matching("Basic .*"));
    }

    private ResponseDefinitionBuilder anInvalidAuthenticationResponse() {
        return aResponse().withStatus(403);
    }

    private String anEncodedBearerToken() throws BearerTokenCreationException {
        String consumerKey = twitterConfig.getConsumerKey();
        String consumerSecret = twitterConfig.getConsumerSecret();
        return twitterKeyUtil.createEncodedBearerToken(consumerKey, consumerSecret);
    }

    private MappingBuilder anAuthenticationRequest() {
        return post(urlEqualTo("/oauth2/token")).
                withHeader("Content-Type", equalTo("application/x-www-form-urlencoded;charset=UTF-8")).
                withRequestBody(equalTo("grant_type=client_credentials"));

    }
}
