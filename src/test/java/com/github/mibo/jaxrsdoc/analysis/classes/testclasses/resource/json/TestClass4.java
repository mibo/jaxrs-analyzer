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
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.util.Collections;
import java.util.Set;

public class TestClass4 {

    @javax.ws.rs.GET
    public JsonObject method() {
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.addNull("key");
        if ("".equals(""))
            objectBuilder.add("value", JsonValue.FALSE);
        objectBuilder.add("test", JsonValue.NULL);
        objectBuilder.add("test", JsonValue.TRUE);
        return objectBuilder.build();
    }

    public static Set<HttpResponse> getResult() {
        final com.github.mibo.jaxrsdoc.model.elements.JsonObject jsonObject = new com.github.mibo.jaxrsdoc.model.elements.JsonObject();
        jsonObject.getStructure().put("key", new Element(Types.OBJECT, null));
        jsonObject.getStructure().put("value", new Element(Types.JSON_VALUE, JsonValue.FALSE));
        jsonObject.getStructure().put("test", new Element(Types.JSON_VALUE, JsonValue.NULL, JsonValue.TRUE));

        final HttpResponse httpResponse = new HttpResponse();
        httpResponse.getEntityTypes().add(Types.JSON_OBJECT);
        httpResponse.getInlineEntities().add(jsonObject);

        return Collections.singleton(httpResponse);
    }

}
