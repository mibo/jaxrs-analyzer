package com.github.mibo.jaxrsdoc.analysis.classes.annotation;

import com.github.mibo.jaxrsdoc.model.results.ClassResult;

/**
 * @author Sebastian Daschner
 */
public class ApplicationPathAnnotationVisitor extends ValueAnnotationVisitor {

    private final ClassResult classResult;

    public ApplicationPathAnnotationVisitor(final ClassResult classResult) {
        this.classResult = classResult;
    }

    @Override
    protected void visitValue(final String value) {
        classResult.setApplicationPath(value);
    }

}
