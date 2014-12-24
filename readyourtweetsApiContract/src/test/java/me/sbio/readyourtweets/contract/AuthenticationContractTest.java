package me.sbio.readyourtweets.contract;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import me.sbio.readyourtweets.twitterapiclient.util.BearerTokenCreationException;
import me.sbio.readyourtweets.twitterapiclient.config.TwitterConfig;
import me.sbio.readyourtweets.twitterapiclient.util.TwitterKeyUtil;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class AuthenticationContractTest {

    private static final String AUTHENTICATION_PATH = "/oauth2/token";

    private final TwitterConfig config;
    private final TwitterKeyUtil twitterKeyUtil;

    public AuthenticationContractTest() {
        config = new TwitterConfig();
        twitterKeyUtil = new TwitterKeyUtil();
    }

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = config.getBaseUri();
        RestAssured.port = config.getPort();
        RestAssured.basePath = config.getBasePath();
    }

    @Test
    public void shouldObtainAnAccessTokenForValidConsumerKeyAndSecret() throws BearerTokenCreationException {
        String consumerKey = config.getConsumerKey();
        String consumerSecret = config.getConsumerSecret();

        String encodedBearerToken = twitterKeyUtil.createEncodedBearerToken(consumerKey, consumerSecret);

        given().
                spec(anAuthenticationRequestSpecification(encodedBearerToken)).
        when().
                post(AUTHENTICATION_PATH).
        then().
                statusCode(200).
                contentType(JSON).
                body("token_type", is("bearer")).
                body("access_token", not(isEmptyOrNullString()));
    }

    @Test
    public void shouldReturnForbiddenForInvalidConsumerKey() throws BearerTokenCreationException {
        String consumerKey = "INVALID_KEY";
        String consumerSecret = config.getConsumerSecret();

        String encodedBearerToken = twitterKeyUtil.createEncodedBearerToken(consumerKey, consumerSecret);

        given().
                spec(anAuthenticationRequestSpecification(encodedBearerToken)).
        when().
                post(AUTHENTICATION_PATH).
        then().
                statusCode(403);

    }

    @Test
    public void shouldReturnForbiddenForInvalidConsumerSecret() throws BearerTokenCreationException {
        String consumerKey = config.getConsumerKey();
        String consumerSecret = "INVALID_SECRET";

        String encodedBearerToken = twitterKeyUtil.createEncodedBearerToken(consumerKey, consumerSecret);

        given().
                spec(anAuthenticationRequestSpecification(encodedBearerToken)).
        when().
                post(AUTHENTICATION_PATH).
        then().
                statusCode(403);

    }

    private RequestSpecification anAuthenticationRequestSpecification(String encodedBearerToken) {
        return new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded;charset=UTF-8")
                .addHeader("Authorization", "Basic " + encodedBearerToken)
                .setBody("grant_type=client_credentials").build();
    }

}
