package me.sbio.readyourtweets.app.handler;

import org.springframework.web.servlet.ModelAndView;

import static com.google.common.base.Strings.isNullOrEmpty;

public class HelloResponse extends ModelAndView {

    private static final String VIEW_NAME = "hello";
    private static final String NAME_ATTRIBUTE = "name";

    public HelloResponse(String name) {
        super(VIEW_NAME);
        addObject(NAME_ATTRIBUTE, name);
    }

    public String getName() {
        return (String) getModel().get(NAME_ATTRIBUTE);
    }

    public boolean hasName() {
        return !isNullOrEmpty(getName());
    }
}
