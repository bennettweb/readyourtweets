package me.sbio.readyourtweets.spec;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;

public class RetrieveUserTweetsRequestSpec implements TwitterRequestSpec {

    private static final String USER_TIMELINE_PATH = "/1.1/statuses/user_timeline.json";

    private RequestSpecBuilder requestSpecBuilder;

    public RetrieveUserTweetsRequestSpec(String accessToken) {
        this.requestSpecBuilder = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + accessToken);
    }

    public RetrieveUserTweetsRequestSpec withScreenName(String screenName) {
        requestSpecBuilder.addQueryParam("screen_name", screenName);
        return this;
    }

    @Override
    public RequestSpecification asRequestSpecification() {
        return requestSpecBuilder.build();
    }

    @Override
    public String path() {
        return USER_TIMELINE_PATH;
    }
}
