package me.sbio.readyourtweets.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TwitterKeyUtil {

    public String createEncodedBearerToken(String consumerKey, String consumerSecret) throws BearerTokenCreationException {
        if (StringUtils.isBlank(consumerKey)) {
            throw new IllegalArgumentException("ConsumerKey is not set");
        }

        if (StringUtils.isBlank(consumerSecret)) {
            throw new IllegalArgumentException("ConsumerSecret is not set");
        }

        // 1. Encode the keys using RFC 1738
        String encodedConsumerKey = null;
        String encodedConsumerSecret = null;
        try {
            encodedConsumerKey = URLEncoder.encode(consumerKey, "UTF-8");
            encodedConsumerSecret = URLEncoder.encode(consumerSecret, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BearerTokenCreationException("Failed to encode key", e);
        }

        // 2. Concatenate using a ':'
        String bearerTokenCredentials = encodedConsumerKey + ":" + encodedConsumerSecret;

        // 3. Base 64 encode
        byte[] encodedBearerCredentials = Base64.encodeBase64(bearerTokenCredentials.getBytes());
        return new String(encodedBearerCredentials);
    }
}
