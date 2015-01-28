package me.sbio.readyourtweets.twitterapiclient.resource.auth;

import me.sbio.readyourtweets.twitterapiclient.resource.EntityFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

public class AuthenticationRequest {

    private final Invocation.Builder request;
    private final Entity<String> authenticationEntity;

    public AuthenticationRequest(WebTarget baseWebTarget, EntityFactory entityFactory, String encodedBearerToken) {
        WebTarget webTarget = baseWebTarget.path("/oauth2/token");
        request = webTarget.request().
                header("Authorization", "Basic " + encodedBearerToken);
        authenticationEntity = entityFactory.newAuthenticationEntity();
    }

    public AuthenticationResponse submit() {
        return request.post(authenticationEntity, AuthenticationResponse.class);
    }

    public Entity<String> getEntity() {
        return authenticationEntity;
    }
}
