package me.sbio.readyourtweets.app.handler;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class EmptyHelloResponseTest {

    private static final String EMPTY_STRING = "";

    @Test
    public void shouldReturnFalseForHasName() {
        assertThat(anEmptyHelloResponse().hasName()).isFalse();
    }

    @Test
    public void shouldReturnAnEmptyStringForName() {
        assertThat(anEmptyHelloResponse().getName()).isEqualTo(EMPTY_STRING);
    }

    @Test
    public void shouldHaveEmptyStringForNameAttributeInMap() {
        assertThat(anEmptyHelloResponse().getModelMap().get("name")).isEqualTo(EMPTY_STRING);
    }

    private EmptyHelloResponse anEmptyHelloResponse() {
        return new EmptyHelloResponse();
    }
}