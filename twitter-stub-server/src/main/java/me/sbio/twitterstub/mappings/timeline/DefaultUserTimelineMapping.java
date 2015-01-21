package me.sbio.twitterstub.mappings.timeline;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import me.sbio.twitterstub.mappings.Mapping;

public class DefaultUserTimelineMapping implements Mapping {

    @Override
    public MappingBuilder request() {
        return new UserTimelineRequest("[A-Za-z0-9_]{1,15}");
    }

    @Override
    public ResponseDefinitionBuilder response() {
        return new FileBasedUserTimelineResponse("retrieve_tweets_response.json");
    }
}
