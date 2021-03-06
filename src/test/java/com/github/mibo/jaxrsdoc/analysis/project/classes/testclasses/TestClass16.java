/*
 * Copyright (C) 2015 Sebastian Daschner, sebastian-daschner.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.mibo.jaxrsdoc.analysis.project.classes.testclasses;

import com.github.mibo.jaxrsdoc.model.rest.HttpMethod;
import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;
import com.github.mibo.jaxrsdoc.builder.ClassResultBuilder;
import com.github.mibo.jaxrsdoc.builder.HttpResponseBuilder;
import com.github.mibo.jaxrsdoc.builder.MethodResultBuilder;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

// not meant to be correct JAX-RS code, just for test purposes
// #59 JSR 339 String auto-convertible types
@Path("test")
public class TestClass16 {

    @DefaultValue("1")
    @QueryParam("id")
    private StringValueOfParam id;

    @GET
    @Path("{info}")
    public Response getInfo(@DefaultValue("1") @QueryParam("value") final StringFromStringParam value) {
        return Response.ok().build();
    }

    public static ClassResult getResult() {
        final MethodResult method = MethodResultBuilder
                .withResponses(HttpResponseBuilder.withStatues(200).build())
                .andPath("{info}")
                .andMethod(HttpMethod.GET)
                .andQueryParam("value", "Lcom/github/mibo/jaxrsdoc/analysis/project/classes/testclasses/TestClass16$StringFromStringParam;", "1")
                .build();

        return ClassResultBuilder
                .withResourcePath("test")
                .andMethods(method)
                .andQueryParam("id", "Lcom/github/mibo/jaxrsdoc/analysis/project/classes/testclasses/TestClass16$StringValueOfParam;", "1")
                .build();
    }

    private static class StringValueOfParam {
        public static StringValueOfParam valueOf(String value) {
            return null;
        }
    }

    private static class StringFromStringParam {
        public static StringFromStringParam fromString(String value) {
            return null;
        }
    }

}
