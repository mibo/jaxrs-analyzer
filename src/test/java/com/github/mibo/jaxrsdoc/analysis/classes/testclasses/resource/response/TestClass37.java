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

package com.github.mibo.jaxrsdoc.analysis.classes.testclasses.resource.response;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.elements.Element;
import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;
import com.github.mibo.jaxrsdoc.model.elements.JsonArray;
import com.github.mibo.jaxrsdoc.model.elements.JsonObject;

import javax.json.Json;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestClass37 {

    @javax.ws.rs.GET
    public Response method() {
        if ("".equals(""))
            return Response.accepted(Json.createArrayBuilder().add(true).add("hello").build()).build();
        return Response.status(200).entity(Json.createObjectBuilder().add("key", "value").add("duke", 42).build()).build();
    }

    public static Set<HttpResponse> getResult() {
        final HttpResponse firstResult = new HttpResponse();
        final HttpResponse secondResult = new HttpResponse();

        firstResult.getStatuses().add(202);
        firstResult.getEntityTypes().add(Types.JSON_ARRAY);

        secondResult.getStatuses().add(200);
        secondResult.getEntityTypes().add(Types.JSON_OBJECT);

        final JsonObject jsonObject = new JsonObject();
        jsonObject.getStructure().put("key", new Element(Types.STRING, "value"));
        jsonObject.getStructure().put("duke", new Element(Types.INTEGER, 42));

        final JsonArray jsonArray = new JsonArray();
        jsonArray.getElements().add(new Element(Types.PRIMITIVE_BOOLEAN, 1));
        jsonArray.getElements().add(new Element(Types.STRING, "hello"));

        firstResult.getInlineEntities().add(jsonArray);
        secondResult.getInlineEntities().add(jsonObject);

        return new HashSet<>(Arrays.asList(firstResult, secondResult));
    }

}
