package me.sbio.readyourtweets.spec;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;

public class TwitterAppAuthenticationRequestSpec implements TwitterRequestSpec {

    private static final String PATH = "/oauth2/token";

    private RequestSpecBuilder requestSpecBuilder;

    public TwitterAppAuthenticationRequestSpec(String encodedBearerToken) {
        requestSpecBuilder = new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Authorization", "Basic " + encodedBearerToken)
                .setBody("grant_type=client_credentials");
    }

    public RequestSpecification asRequestSpecification() {
        return requestSpecBuilder.build();
    }

    @Override
    public String path() {
        return PATH;
    }
}
