package me.sbio.readyourtweets.app.handler;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class HelloHandlerTest {

    private final HelloHandler helloHandler = new HelloHandler();

    @Test
    public void shouldReturnEmptyNameWhenInputNameIsEmpty() {
        HelloResponse helloResponse = helloHandler.sayHello("");

        assertThat(helloResponse.hasName()).isFalse();
    }

    @Test
    public void shouldReturnEmptyNameWhenInputNameIsNull() {
        HelloResponse helloResponse = helloHandler.sayHello(null);

        assertThat(helloResponse.hasName()).isFalse();
    }

    @Test
    public void shouldReturnNameWhenInputNameIsSet() {
        HelloResponse helloResponse = helloHandler.sayHello("sbio");

        assertThat(helloResponse.getName()).isEqualTo("sbio");
    }

}