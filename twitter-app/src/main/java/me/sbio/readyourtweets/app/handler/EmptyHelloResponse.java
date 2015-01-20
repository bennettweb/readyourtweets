package me.sbio.readyourtweets.app.handler;

public class EmptyHelloResponse extends HelloResponse {

    public static String EMPTY_NAME = "";

    public EmptyHelloResponse() {
        super(EMPTY_NAME);
    }

    @Override
    public boolean hasName() {
        return false;
    }

    @Override
    public String getName() {
        return EMPTY_NAME;
    }
}
