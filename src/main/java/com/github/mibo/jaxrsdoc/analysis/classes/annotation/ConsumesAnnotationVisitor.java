package com.github.mibo.jaxrsdoc.analysis.classes.annotation;

import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;

/**
 * @author Sebastian Daschner
 */
public class ConsumesAnnotationVisitor extends ClassAndMethodAnnotationVisitor {

    public ConsumesAnnotationVisitor(final ClassResult classResult) {
        super(classResult);
    }

    public ConsumesAnnotationVisitor(final MethodResult methodResult) {
        super(methodResult);
    }

    @Override
    protected void visitValue(final String value, final ClassResult classResult) {
        classResult.getRequestMediaTypes().add(value);
    }

    @Override
    protected void visitValue(final String value, final MethodResult methodResult) {
        methodResult.getRequestMediaTypes().add(value);
    }

}
