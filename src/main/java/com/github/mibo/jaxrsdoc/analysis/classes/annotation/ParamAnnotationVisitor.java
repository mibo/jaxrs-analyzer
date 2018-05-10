package com.github.mibo.jaxrsdoc.analysis.classes.annotation;

import com.github.mibo.jaxrsdoc.model.rest.MethodParameter;

/**
 * @author Sebastian Daschner
 */
public class ParamAnnotationVisitor extends ValueAnnotationVisitor {

    private final MethodParameter parameter;

    public ParamAnnotationVisitor(final MethodParameter parameter) {
        this.parameter = parameter;
    }

    @Override
    protected void visitValue(final String value) {
        parameter.setName(value);
    }

}
