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

package com.github.mibo.jaxrsdoc.analysis.classes.testclasses.resource.json;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.elements.Element;
import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.Collections;
import java.util.Set;

public class TestClass6 {

    @javax.ws.rs.GET
    public JsonObject method() {
        final JsonArray array = Json.createArrayBuilder().add(true).add("duke").build();
        return Json.createObjectBuilder()
                .add("array", array)
                .add("int", 42)
                .add("boolean", true)
                .add("long", 100000000000000L)
                .add("double", 1.2d)
                .add("jsonObject", Json.createObjectBuilder().add("key", "value").add("test", 1).build())
                .add("jsonArray", Json.createArrayBuilder().add("first").add("second").add(3d)
                        .add(Json.createObjectBuilder().add("key", "object").build())
                        .add(Json.createObjectBuilder().add("nested", "object")))
                .build();
    }

    public static Set<HttpResponse> getResult() {

        final com.github.mibo.jaxrsdoc.model.elements.JsonObject jsonObject = new com.github.mibo.jaxrsdoc.model.elements.JsonObject();
        final com.github.mibo.jaxrsdoc.model.elements.JsonArray jsonArray = new com.github.mibo.jaxrsdoc.model.elements.JsonArray();
        jsonArray.getElements().add(new Element(Types.PRIMITIVE_BOOLEAN, 1));
        jsonArray.getElements().add(new Element(Types.STRING, "duke"));
        jsonObject.getStructure().put("array", new Element(Types.JSON_ARRAY, jsonArray));
        jsonObject.getStructure().put("int", new Element(Types.INTEGER, 42));
        jsonObject.getStructure().put("boolean", new Element(Types.PRIMITIVE_BOOLEAN, 1));
        jsonObject.getStructure().put("long", new Element(Types.LONG, 100000000000000L));
        jsonObject.getStructure().put("double", new Element(Types.DOUBLE, 1.2d));

        final com.github.mibo.jaxrsdoc.model.elements.JsonObject object = new com.github.mibo.jaxrsdoc.model.elements.JsonObject();
        object.getStructure().put("key", new Element(Types.STRING, "value"));
        object.getStructure().put("test", new Element(Types.INTEGER, 1));
        jsonObject.getStructure().put("jsonObject", new Element(Types.JSON_OBJECT, object));

        final com.github.mibo.jaxrsdoc.model.elements.JsonArray array = new com.github.mibo.jaxrsdoc.model.elements.JsonArray();
        array.getElements().add(new Element(Types.STRING, "first"));
        array.getElements().add(new Element(Types.STRING, "second"));
        array.getElements().add(new Element(Types.DOUBLE, 3d));

        final com.github.mibo.jaxrsdoc.model.elements.JsonObject firstNested = new com.github.mibo.jaxrsdoc.model.elements.JsonObject();
        firstNested.getStructure().put("key", new Element(Types.STRING, "object"));
        final com.github.mibo.jaxrsdoc.model.elements.JsonObject secondNested = new com.github.mibo.jaxrsdoc.model.elements.JsonObject();
        secondNested.getStructure().put("nested", new Element(Types.STRING, "object"));

        array.getElements().add(new Element(Types.JSON_OBJECT, firstNested));
        array.getElements().add(new Element(Types.JSON_OBJECT, secondNested));
        jsonObject.getStructure().put("jsonArray", new Element(Types.JSON_ARRAY, array));

        final HttpResponse httpResponse = new HttpResponse();
        httpResponse.getEntityTypes().add(Types.JSON_OBJECT);
        httpResponse.getInlineEntities().add(jsonObject);

        return Collections.singleton(httpResponse);
    }

}
