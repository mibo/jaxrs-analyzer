package com.github.mibo.jaxrsdoc.analysis.bytecode.subresource;

import javax.ws.rs.container.ResourceContext;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.singleton;

public class TestClass5 {

    public Object method() {
        ResourceContext rc = null;
        if ("".equals(""))
            return rc.getResource(AnotherSubResource.class);
        return rc.getResource(SubResource.class);
    }

    public static Set<String> getResult() {
        // FEATURE test several resources
//        return new HashSet<>(Arrays.asList("com/github/mibo/jaxrsdoc/analysis/bytecode/subresource/TestClass5$SubResource",
//                "com/github/mibo/jaxrsdoc/analysis/bytecode/subresource/TestClass5$AnotherSubResource"));
        return new HashSet<>(singleton("com/github/mibo/jaxrsdoc/analysis/bytecode/subresource/TestClass5$SubResource"));
    }

    private class SubResource {
    }

    private class AnotherSubResource {
    }

}
