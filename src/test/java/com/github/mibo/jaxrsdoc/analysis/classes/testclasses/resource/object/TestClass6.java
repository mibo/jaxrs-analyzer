package com.github.mibo.jaxrsdoc.analysis.classes.testclasses.resource.object;

import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;

import java.util.*;

public class TestClass6 {

    @javax.ws.rs.GET public List<Model> method() {
        if ("".equals(""))
            return new ArrayList<>();
        return new LinkedList<>();
    }

    public static Set<HttpResponse> getResult() {
        final HttpResponse result = new HttpResponse();
        result.getEntityTypes().add("Ljava/util/List<Lcom/github/mibo/jaxrsdoc/analysis/classes/testclasses/resource/object/TestClass6$Model;>;");

        return Collections.singleton(result);
    }

    private class Model {
        public Model(final String string) {
        }
    }

}
