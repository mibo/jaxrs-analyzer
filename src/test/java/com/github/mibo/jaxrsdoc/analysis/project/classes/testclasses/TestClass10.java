package com.github.mibo.jaxrsdoc.analysis.project.classes.testclasses;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.rest.HttpMethod;
import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;
import com.github.mibo.jaxrsdoc.builder.ClassResultBuilder;
import com.github.mibo.jaxrsdoc.builder.HttpResponseBuilder;
import com.github.mibo.jaxrsdoc.builder.MethodResultBuilder;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("test")
public class TestClass10 extends ATestClass10 {

    @Override
    public Response getInfo(final String info) {
        final Map<String, Integer> map = new HashMap<>();
        map.put(info, 100);
        return createResponse(map);
    }

    public static ClassResult getResult() {
        final MethodResult method = MethodResultBuilder.withResponses(HttpResponseBuilder.withStatues(200).andEntityTypes(Types.STRING).andHeaders("X-Test").build())
                .andPath("{info}").andMethod(HttpMethod.POST).andRequestBodyType(Types.STRING).build();
        return ClassResultBuilder.withResourcePath("test").andMethods(method).build();
    }

}

abstract class ATestClass10 {

    @POST
    @Path("{info}")
    public abstract Response getInfo(final String info);

    protected final Response createResponse(final Map<String, Integer> map) {
        return Response.ok("hello").header("X-Test", "world").build();
    }

}
