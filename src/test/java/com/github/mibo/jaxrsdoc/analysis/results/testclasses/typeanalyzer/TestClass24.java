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

package com.github.mibo.jaxrsdoc.analysis.results.testclasses.typeanalyzer;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.rest.TypeIdentifier;
import com.github.mibo.jaxrsdoc.model.rest.TypeRepresentation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.*;

/**
 * Test @JsonIgnoreType
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TestClass24 {

    private int first;
    private InnerTestClass child;

    @JsonIgnoreType
    private class InnerTestClass {
        private int second;
        private TestClass24 child;
    }

    public static Set<TypeRepresentation> expectedTypeRepresentations() {
        final Map<String, TypeIdentifier> properties = new HashMap<>();

        properties.put("first", TypeIdentifier.ofType(Types.PRIMITIVE_INT));

        final TypeIdentifier testClass24Identifier = expectedIdentifier();
        final TypeRepresentation testClass24 = TypeRepresentation.ofConcrete(testClass24Identifier, properties);


        return new HashSet<>(Arrays.asList(testClass24));
    }

    public static TypeIdentifier expectedIdentifier() {
        return TypeIdentifier.ofType("Lcom/github/mibo/jaxrsdoc/analysis/results/testclasses/typeanalyzer/TestClass24;");
    }

}
