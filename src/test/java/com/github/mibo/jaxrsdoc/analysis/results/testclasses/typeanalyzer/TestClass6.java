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

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.rest.TypeIdentifier;
import com.github.mibo.jaxrsdoc.model.rest.TypeRepresentation;
import com.github.mibo.jaxrsdoc.analysis.results.TypeUtils;

import javax.xml.bind.annotation.XmlElement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestClass6 {

    private boolean first;
    private String second;

    public boolean isFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    // not taken, wrong return type
    public void getFake() {
    }

    public String isFake() {
        return second;
    }

    @XmlElement
    private String getThird() {
        return second;
    }

    public static Set<TypeRepresentation> expectedTypeRepresentations() {
        final Map<String, TypeIdentifier> properties = new HashMap<>();

        properties.put("first", TypeIdentifier.ofType(Types.PRIMITIVE_BOOLEAN));
        properties.put("second", TypeUtils.STRING_IDENTIFIER);
        properties.put("third", TypeUtils.STRING_IDENTIFIER);

        return Collections.singleton(TypeRepresentation.ofConcrete(expectedIdentifier(), properties));
    }

    public static TypeIdentifier expectedIdentifier() {
        return TypeIdentifier.ofType("Lcom/github/mibo/jaxrsdoc/analysis/results/testclasses/typeanalyzer/TestClass6;");
    }

}
