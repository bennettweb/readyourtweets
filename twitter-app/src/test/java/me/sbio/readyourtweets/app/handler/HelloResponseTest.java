package me.sbio.readyourtweets.app.handler;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class HelloResponseTest {

    private static final String NAME = "sbio";

    @Test
    public void shouldReturnTrueForHasName() {
        assertThat(aHelloResponse().hasName()).isTrue();
    }

    @Test
    public void shouldReturnNameSetFromGetName() {
        assertThat(aHelloResponse().getName()).isEqualTo(NAME);
    }

    @Test
    public void shouldHaveNameAttributeSetInModel() {
        assertThat(aHelloResponse().getModel().get("name")).isEqualTo(NAME);
    }

    private HelloResponse aHelloResponse() {
        return new HelloResponse(NAME);
    }
}