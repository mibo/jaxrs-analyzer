package com.github.mibo.jaxrsdoc.analysis.classes.annotation;

import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;

/**
 * @author Sebastian Daschner
 */
public class PathAnnotationVisitor extends ClassAndMethodAnnotationVisitor {

    public PathAnnotationVisitor(final ClassResult classResult) {
        super(classResult);
    }

    public PathAnnotationVisitor(final MethodResult methodResult) {
        super(methodResult);
    }

    @Override
    protected void visitValue(final String value, final ClassResult classResult) {
        classResult.setResourcePath(value);
    }

    @Override
    protected void visitValue(final String value, final MethodResult methodResult) {
        methodResult.setPath(value);
    }

}
