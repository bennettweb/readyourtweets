package me.sbio.twitterstub.mappings;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.google.common.io.ByteStreams;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static me.sbio.twitterstub.TwitterStubServerParams.ACCESS_TOKEN;

public class RetrieveTweetsMappings implements Mapping {

    @Override
    public void register() throws MappingRegistrationException {
        stubFor(aRetrieveUserTweetsRequest().willReturn(aRetrieveTweetsResponse()));
    }

    private MappingBuilder aRetrieveUserTweetsRequest() {
        return get(urlMatching("/1.1/statuses/user_timeline.json\\?screen_name=[A-Za-z0-9_]{1,15}")).
                withHeader("Authorization", equalTo("Bearer " + ACCESS_TOKEN));
    }

    private ResponseDefinitionBuilder aRetrieveTweetsResponse() {
        return aResponse().
                withStatus(200).
                withBody(fromFile("retrieve_tweets_response.json")).
                withHeader("Content-Type", "application/json; charset=UTF-8");
    }

    private byte[] fromFile(String filename) {
        try {
            return ByteStreams.toByteArray(this.getClass().getResourceAsStream("/" + filename));
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to find file " + filename);
        }
    }

}
