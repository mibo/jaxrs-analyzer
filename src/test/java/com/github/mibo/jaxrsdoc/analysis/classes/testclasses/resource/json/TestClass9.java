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
import com.github.mibo.jaxrsdoc.model.elements.JsonArray;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TestClass9 {

    @javax.ws.rs.GET public javax.json.JsonArray method() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        stateMachineNames().forEach(builder::add);
        return builder.build();
    }

    private List<String> stateMachineNames() {
        return Collections.emptyList();
    }

    public static Set<HttpResponse> getResult() {
        final JsonArray jsonArray = new JsonArray();
        jsonArray.getElements().add(new Element(Types.STRING));

        final HttpResponse httpResponse = new HttpResponse();
        httpResponse.getEntityTypes().add(Types.JSON_ARRAY);
        httpResponse.getInlineEntities().add(jsonArray);

        return Collections.singleton(httpResponse);
    }

}
