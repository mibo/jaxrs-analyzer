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

package com.github.mibo.jaxrsdoc.builder;

import com.github.mibo.jaxrsdoc.model.rest.ResourceMethod;
import com.github.mibo.jaxrsdoc.model.rest.Resources;
import com.github.mibo.jaxrsdoc.model.rest.TypeIdentifier;
import com.github.mibo.jaxrsdoc.model.rest.TypeRepresentation;

import java.util.stream.Stream;

/**
 * @author Sebastian Daschner
 */
public class ResourcesBuilder {

    private final Resources resources;

    private ResourcesBuilder() {
        resources = new Resources();
    }

    public static ResourcesBuilder withBase(final String baseUri) {
        final ResourcesBuilder builder = new ResourcesBuilder();
        builder.resources.setBasePath(baseUri);
        return builder;
    }

    public ResourcesBuilder andResource(final String resource, final ResourceMethod... method) {
        Stream.of(method).forEach(m -> resources.addMethod(resource, m));
        return this;
    }

    public ResourcesBuilder andTypeRepresentation(final TypeIdentifier identifier, final TypeRepresentation representation) {
        resources.getTypeRepresentations().put(identifier, representation);
        return this;
    }

    public Resources build() {
        return resources;
    }

}
