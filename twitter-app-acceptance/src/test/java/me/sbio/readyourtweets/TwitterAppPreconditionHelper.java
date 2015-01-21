package me.sbio.readyourtweets;

import me.sbio.readyourtweets.domain.TwitterUserFixture;
import me.sbio.twitterstub.TwitterStubServer;
import me.sbio.twitterstub.mappings.Mapping;
import me.sbio.twitterstub.mappings.MappingRegistrationException;
import me.sbio.twitterstub.mappings.timeline.UserTimelineMapping;

public class TwitterAppPreconditionHelper {

    private final TwitterStubServer twitterStubServer;

    public TwitterAppPreconditionHelper(TwitterStubServer twitterStubServer) {
        this.twitterStubServer = twitterStubServer;
    }

    public void ensure(final TwitterUserFixture userFixture) throws MappingRegistrationException {
        Mapping userTimelineMapping = new UserTimelineMapping(userFixture.getScreenname(), userFixture.getTweets());
        twitterStubServer.registerMappings(userTimelineMapping);
    }
}
