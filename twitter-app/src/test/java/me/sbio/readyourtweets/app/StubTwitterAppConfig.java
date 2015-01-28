package me.sbio.readyourtweets.app;

import me.sbio.readyourtweets.commons.config.StubTwitterConfig;
import me.sbio.readyourtweets.commons.config.TwitterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StubTwitterAppConfig {

    @Bean
    public TwitterConfig twitterConfig() {
        return new StubTwitterConfig();
    }
}
