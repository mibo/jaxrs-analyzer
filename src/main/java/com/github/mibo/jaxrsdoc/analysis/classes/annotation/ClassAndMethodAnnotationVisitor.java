package com.github.mibo.jaxrsdoc.analysis.classes.annotation;

import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;

import java.util.Objects;

/**
 * @author Sebastian Daschner
 */
abstract class ClassAndMethodAnnotationVisitor extends ValueAnnotationVisitor {

    private final ClassResult classResult;
    private final MethodResult methodResult;

    ClassAndMethodAnnotationVisitor(final ClassResult classResult) {
        this(classResult, null);
        Objects.requireNonNull(classResult);
    }

    ClassAndMethodAnnotationVisitor(final MethodResult methodResult) {
        this(null, methodResult);
        Objects.requireNonNull(methodResult);
    }

    private ClassAndMethodAnnotationVisitor(final ClassResult classResult, final MethodResult methodResult) {
        this.classResult = classResult;
        this.methodResult = methodResult;
    }

    protected abstract void visitValue(String value, ClassResult classResult);

    protected abstract void visitValue(String value, MethodResult methodResult);

    @Override
    protected final void visitValue(final String value) {
        if (classResult != null)
            visitValue(value, classResult);
        else
            visitValue(value, methodResult);
    }

}
