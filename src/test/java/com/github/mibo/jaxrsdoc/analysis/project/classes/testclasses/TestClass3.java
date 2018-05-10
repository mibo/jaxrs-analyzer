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

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.elements.Element;
import com.github.mibo.jaxrsdoc.model.rest.HttpMethod;
import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;
import com.github.mibo.jaxrsdoc.builder.ClassResultBuilder;
import com.github.mibo.jaxrsdoc.builder.HttpResponseBuilder;
import com.github.mibo.jaxrsdoc.builder.MethodResultBuilder;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
@Produces(MediaType.APPLICATION_JSON)
public class TestClass3 {

    @GET
    public JsonObject method() {
        return Json.createObjectBuilder().add("key", "value").add("duke", 42).build();
    }

    public static ClassResult getResult() {
        final com.github.mibo.jaxrsdoc.model.elements.JsonObject jsonObject = new com.github.mibo.jaxrsdoc.model.elements.JsonObject();
        jsonObject.getStructure().put("key", new Element(Types.STRING, "value"));
        jsonObject.getStructure().put("duke", new Element(Types.INTEGER, 42));
        final MethodResult firstMethod = MethodResultBuilder.withResponses(HttpResponseBuilder.newBuilder().andEntityTypes(Types.JSON_OBJECT)
                .andInlineEntities(jsonObject).build())
                .andMethod(HttpMethod.GET).build();
        return ClassResultBuilder.withResourcePath("test").andResponseMediaTypes("application/json").andMethods(firstMethod).build();
    }

}
