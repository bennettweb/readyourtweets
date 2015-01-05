package me.sbio.twitterstub.mappings.timeline;

import me.sbio.twitterstub.mappings.Mapping;
import me.sbio.twitterstub.mappings.MappingRegistrationException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class UserTimelineMapping implements Mapping {

    @Override
    public void register() throws MappingRegistrationException {
        stubFor(new UserTimelineRequest("[A-Za-z0-9_]{1,15}").willReturn(new UserTimelineResponse("retrieve_tweets_response.json")));
    }

}
