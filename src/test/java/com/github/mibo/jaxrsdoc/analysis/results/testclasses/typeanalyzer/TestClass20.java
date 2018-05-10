package com.github.mibo.jaxrsdoc.analysis.results.testclasses.typeanalyzer;

import com.github.mibo.jaxrsdoc.model.rest.TypeIdentifier;
import com.github.mibo.jaxrsdoc.model.rest.TypeRepresentation;
import com.github.mibo.jaxrsdoc.analysis.results.TypeUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestClass20 implements Interface1, Interface2 {

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
        properties.put("test1", TypeUtils.STRING_IDENTIFIER);
        properties.put("test2", TypeUtils.STRING_IDENTIFIER);
        properties.put("test3", TypeUtils.STRING_IDENTIFIER);
        properties.put("test4", TypeUtils.STRING_IDENTIFIER);

        return Collections.singleton(TypeRepresentation.ofConcrete(expectedIdentifier(), properties));
    }

    public static TypeIdentifier expectedIdentifier() {
        return TypeIdentifier.ofType("Lcom/github/mibo/jaxrsdoc/analysis/results/testclasses/typeanalyzer/TestClass20;");
    }

    @Override
    public String getTest1() {
        return null;
    }

    @Override
    public String getTest2() {
        return null;
    }
}

interface Interface1 {

    String getTest1();

}

interface Interface2 extends Interface3 {

    String getTest2();

    default String getTest3() {
        return "string default";
    }
}

interface Interface3 {

    Object getTest3();

    default String getTest4() {
        return "default";
    }
}
