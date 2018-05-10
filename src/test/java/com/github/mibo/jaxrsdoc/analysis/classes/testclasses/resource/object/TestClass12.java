package com.github.mibo.jaxrsdoc.analysis.classes.testclasses.resource.object;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;
import com.github.mibo.jaxrsdoc.builder.HttpResponseBuilder;

import java.util.Collections;
import java.util.Set;

public class TestClass12<T> {

    private T object;

    @javax.ws.rs.GET public String method(final T body) {
        final T object = this.object;
        return "hello " + object + ", " + body.toString();
    }

    public static Set<HttpResponse> getResult() {
        return Collections.singleton(HttpResponseBuilder.newBuilder().andEntityTypes(Types.STRING).build());
    }

}
