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

import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestClass14 {

    @javax.ws.rs.GET public Response method(final String id) {
        final int status = getStatus();

        if ("".equals(""))
            throw new WebApplicationException(String.valueOf(status), Response.Status.BAD_REQUEST);

        return Response.status(status).build();
    }

    private int getStatus() {
        return 201;
    }

    public static Set<HttpResponse> getResult() {
        final HttpResponse firstResponse = new HttpResponse();
        firstResponse.getStatuses().add(201);

        final HttpResponse secondResponse = new HttpResponse();
        secondResponse.getStatuses().add(400);

        return new HashSet<>(Arrays.asList(firstResponse, secondResponse));
    }

}
