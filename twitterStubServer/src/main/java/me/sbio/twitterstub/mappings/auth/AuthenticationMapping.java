package me.sbio.twitterstub.mappings.auth;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import me.sbio.readyourtweets.twitterapiclient.config.TwitterConfig;
import me.sbio.readyourtweets.twitterapiclient.util.BearerTokenCreationException;
import me.sbio.readyourtweets.twitterapiclient.util.TwitterKeyUtil;
import me.sbio.twitterstub.mappings.Mapping;
import me.sbio.twitterstub.mappings.MappingRegistrationException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

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

    private void registerValidAuthenticationMapping() throws MappingRegistrationException {
        try {
            stubFor(aValidAuthenticationRequest().atPriority(1).willReturn(new ValidAuthenticationResponse()));
        } catch (BearerTokenCreationException e) {
            throw new MappingRegistrationException("Failed to register mapping for valid twitter auth endpoint", e);
        }
    }

    private void registerInvalidAuthenticationMapping() {
        stubFor(anInvalidAuthenticationRequest().atPriority(2).willReturn(new InvalidAuthenticationResponse()));
    }

    private MappingBuilder aValidAuthenticationRequest() throws BearerTokenCreationException {
        return new AuthenticationRequest().withEncodedBearerTokenMatching(anEncodedBearerToken());
    }

    private MappingBuilder anInvalidAuthenticationRequest() {
        return new AuthenticationRequest().withEncodedBearerTokenMatching(".*");
    }

    private String anEncodedBearerToken() throws BearerTokenCreationException {
        String consumerKey = twitterConfig.getConsumerKey();
        String consumerSecret = twitterConfig.getConsumerSecret();
        return twitterKeyUtil.createEncodedBearerToken(consumerKey, consumerSecret);
    }


}
