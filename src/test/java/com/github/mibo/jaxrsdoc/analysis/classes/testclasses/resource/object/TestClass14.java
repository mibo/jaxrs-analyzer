package com.github.mibo.jaxrsdoc.analysis.classes.testclasses.resource.object;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;
import com.github.mibo.jaxrsdoc.builder.HttpResponseBuilder;

import java.util.Collections;
import java.util.Set;

public class TestClass14<T> {

    private TestManager<String> manager;

    @javax.ws.rs.GET public String method(final T body) {
        final String test = manager.getTest(body);
        System.out.println(test.length());
        return test;
    }

    public static Set<HttpResponse> getResult() {
        return Collections.singleton(HttpResponseBuilder.newBuilder().andEntityTypes(Types.STRING).build());
    }

    private static class TestManager<T> {

        private T object;

        public <U> T getTest(final U object) {
            final U otherObject = object;
            final T thisObject = this.object;
            System.out.println(otherObject);
            System.out.println(thisObject);
            return thisObject;
        }
    }

}
