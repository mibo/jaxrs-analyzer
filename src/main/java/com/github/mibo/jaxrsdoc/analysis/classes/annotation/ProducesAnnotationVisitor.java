package com.github.mibo.jaxrsdoc.analysis.classes.annotation;

import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;

/**
 * @author Sebastian Daschner
 */
public class ProducesAnnotationVisitor extends ClassAndMethodAnnotationVisitor {

    public ProducesAnnotationVisitor(final ClassResult classResult) {
        super(classResult);
    }

    public ProducesAnnotationVisitor(final MethodResult methodResult) {
        super(methodResult);
    }

    @Override
    protected void visitValue(final String value, final ClassResult classResult) {
        classResult.getResponseMediaTypes().add(value);
    }

    @Override
    protected void visitValue(final String value, final MethodResult methodResult) {
        methodResult.getResponseMediaTypes().add(value);
    }

}
