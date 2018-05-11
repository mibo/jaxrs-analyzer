package com.github.mibo.jaxrsdoc.analysis.classes.testclasses.resource.object;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;
import com.github.mibo.jaxrsdoc.builder.HttpResponseBuilder;

import java.util.Collections;
import java.util.Set;

public class TestClass11 {

    @javax.ws.rs.GET public String method() {
        final String service = getInstance(String.class);
        return "hello " + service;
    }

    public <T> T getInstance(final Class<T> clazz) {
        return (T) new Object();
    }

    public static Set<HttpResponse> getResult() {
        return Collections.singleton(HttpResponseBuilder.newBuilder().andEntityTypes(Types.STRING).build());
    }

}
