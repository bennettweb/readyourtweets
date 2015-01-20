package me.sbio.readyourtweets;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.Map;

public class UrlBuilder {

    private String baseUrl;
    private LinkedList<String> segments;
    private Map<String, String> parameters;

    public UrlBuilder(String baseUrl) {
        this.segments = Lists.newLinkedList();
        this.parameters = Maps.newHashMap();
        this.baseUrl = baseUrl;
    }

    public UrlBuilder withPathSegment(String segment) {
        this.segments.add(segment);
        return this;
    }

    public UrlBuilder withParameter(String name, String value) {
        parameters.put(name, value);
        return this;
    }

    public String build() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(baseUrl);

        if (!buffer.toString().endsWith("/")) {
            buffer.append("/");
        }

        buffer.append(path(segments));
        buffer.append(queryString(parameters));

        return buffer.toString();
    }

    private String queryString(Map<String, String> parameters) {
        if (parameters.size() == 0) {
            return "";
        }

        return "?" + Joiner.on("&").withKeyValueSeparator("=").join(parameters.entrySet());
    }

    private String path(LinkedList<String> segments) {
        return Joiner.on("/").skipNulls().join(segments).replaceAll("//", "/");
    }

}
