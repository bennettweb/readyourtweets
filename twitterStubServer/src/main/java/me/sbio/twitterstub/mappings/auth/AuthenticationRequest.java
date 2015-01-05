package me.sbio.twitterstub.mappings.auth;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.http.RequestMethod;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

class AuthenticationRequest extends MappingBuilder {

    public AuthenticationRequest() {
        super(RequestMethod.POST, urlEqualTo("/oauth2/token"));
        withHeader("Content-Type", equalTo("application/x-www-form-urlencoded;charset=UTF-8"));
        withRequestBody(equalTo("grant_type=client_credentials"));
    }

    public AuthenticationRequest withEncodedBearerTokenMatching(String encodedBearerToken) {
        withHeader("Authorization", matching("Basic " + encodedBearerToken));
        return this;
    }
}
