package me.sbio.readyourtweets.app.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

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
        return !StringUtils.isBlank(getName());
    }
}
