package com.github.mibo.jaxrsdoc.analysis.results.testclasses.typeanalyzer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.mibo.jaxrsdoc.model.rest.TypeIdentifier;
import com.github.mibo.jaxrsdoc.model.rest.TypeRepresentation;
import com.github.mibo.jaxrsdoc.analysis.results.TypeUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @JsonIgnore in superclass method
 */
public class TestClass28 extends SuperTestClass28 {

    private String foobar;

    public String getFoobar() {
        return foobar;
    }

    public void setFoobar(String foobar) {
        this.foobar = foobar;
    }

    public static Set<TypeRepresentation> expectedTypeRepresentations() {
        final Map<String, TypeIdentifier> properties = new HashMap<>();

        properties.put("foobar", TypeUtils.STRING_IDENTIFIER);

        return Collections.singleton(TypeRepresentation.ofConcrete(expectedIdentifier(), properties));
    }

    public static TypeIdentifier expectedIdentifier() {
        return TypeIdentifier.ofType("Lcom/github/mibo/jaxrsdoc/analysis/results/testclasses/typeanalyzer/TestClass28;");
    }

}


class SuperTestClass28 {

    private String test;

    @JsonIgnore
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

}