package com.github.mibo.jaxrsdoc.analysis.classes.testclasses.resource.object;

import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;

import java.util.Collections;
import java.util.Set;

public class TestClass4 {

    @javax.ws.rs.GET
    public Model method() {
        if ("".equals(""))
            return new Model("hi");
        return new Model("Hello World!");
    }

    public static Set<HttpResponse> getResult() {
        final HttpResponse result = new HttpResponse();
        result.getEntityTypes().add("Lcom/github/mibo/jaxrsdoc/analysis/classes/testclasses/resource/object/TestClass4$Model;");

        return Collections.singleton(result);
    }

    private class Model {
        public Model(final String string) {
        }
    }

}
