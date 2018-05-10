package com.github.mibo.jaxrsdoc.analysis.bytecode;

import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;

/**
 * @author Sebastian Daschner
 */
public class BytecodeAnalyzer {

    private final ResourceMethodContentAnalyzer methodContentAnalyzer = new ResourceMethodContentAnalyzer();
    private final SubResourceLocatorMethodContentAnalyzer subResourceLocatorAnalyzer = new SubResourceLocatorMethodContentAnalyzer();

    /**
     * Analyzes the bytecode instructions of the method results and interprets JAX-RS relevant information.
     */
    public void analyzeBytecode(final ClassResult classResult) {
        classResult.getMethods().forEach(this::analyzeBytecode);
    }

    private void analyzeBytecode(final MethodResult methodResult) {
        if (methodResult.getHttpMethod() == null) {
            // sub-resource
            subResourceLocatorAnalyzer.analyze(methodResult);
        } else {
            methodContentAnalyzer.analyze(methodResult);
        }
    }

}
