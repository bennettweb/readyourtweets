package me.sbio.readyourtweets.contract;

import me.sbio.readyourtweets.AuthenticationResponse;
import me.sbio.readyourtweets.spec.RetrieveUserTweetsRequestSpec;
import me.sbio.readyourtweets.spec.TwitterAppAuthenticationRequestSpec;
import me.sbio.readyourtweets.twitterapiclient.util.BearerTokenCreationException;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;

public class RetrieveTweetsContactTest extends ContractTest {

    private static final String SCREEN_NAME = "twitterapi";

    @Test
    public void shouldRetrieveTweetsForAUser() throws BearerTokenCreationException {
        String consumerKey = getTwitterConfig().getConsumerKey();
        String consumerSecret = getTwitterConfig().getConsumerSecret();

        String encodedBearerToken = getTwitterKeyUtil().createEncodedBearerToken(consumerKey, consumerSecret);
        String accessToken = generateAccessToken(encodedBearerToken);

        RetrieveUserTweetsRequestSpec userTweetsRequestSpec = aRetrieveTweetRequestSpecification(accessToken, SCREEN_NAME);

        given().
                spec(userTweetsRequestSpec.asRequestSpecification()).
        when().
                get(userTweetsRequestSpec.path()).
        then().
                statusCode(200).
                contentType(JSON);
    }

    private String generateAccessToken(String encodedBearerToken) {
        TwitterAppAuthenticationRequestSpec authenticationRequestSpec = new TwitterAppAuthenticationRequestSpec(encodedBearerToken);

        AuthenticationResponse authenticationResponse = given().
                    spec(authenticationRequestSpec.asRequestSpecification()).
                when().
                    post(authenticationRequestSpec.path()).as(AuthenticationResponse.class);

        return authenticationResponse.getAccessToken();
    }

    private RetrieveUserTweetsRequestSpec aRetrieveTweetRequestSpecification(String accessToken, String screenName) {
        return new RetrieveUserTweetsRequestSpec(accessToken).withScreenName(screenName);
    }

}
