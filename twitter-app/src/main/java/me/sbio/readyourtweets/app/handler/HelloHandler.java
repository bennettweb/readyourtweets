package me.sbio.readyourtweets.app.handler;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class HelloHandler {

    public HelloResponse sayHello(String name) {
        if (StringUtils.isEmpty(name)) {
            return new EmptyHelloResponse();
        }
        return new HelloResponse(name);
    }
}
