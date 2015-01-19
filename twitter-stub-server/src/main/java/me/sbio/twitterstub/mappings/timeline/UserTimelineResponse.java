package me.sbio.twitterstub.mappings.timeline;

import me.sbio.twitterstub.mappings.FileBasedResponse;

class UserTimelineResponse extends FileBasedResponse {

    public UserTimelineResponse(String filename) {
        super(filename);
        withStatus(200);
        withHeader("Content-Type", "application/json; charset=UTF-8");
    }

}
