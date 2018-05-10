package com.github.mibo.jaxrsdoc.analysis.classes.testclasses.resource.object;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;

import java.util.Collections;
import java.util.Set;

public class TestClass1 {

    @javax.ws.rs.GET
    public String method() {
        return "Hello World!";
    }

    public static Set<HttpResponse> getResult() {
        final HttpResponse result = new HttpResponse();
        result.getEntityTypes().add(Types.STRING);

        return Collections.singleton(result);
    }

}
