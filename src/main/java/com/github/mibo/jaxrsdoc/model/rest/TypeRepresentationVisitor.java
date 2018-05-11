package com.github.mibo.jaxrsdoc.model.rest;

/**
 * Visitor for the {@link TypeRepresentation} implementations.
 *
 * @author Sebastian Daschner
 */
public interface TypeRepresentationVisitor {

    void visit(TypeRepresentation.ConcreteTypeRepresentation representation);

    void visitStart(TypeRepresentation.CollectionTypeRepresentation representation);
    void visitEnd(TypeRepresentation.CollectionTypeRepresentation representation);

    void visit(TypeRepresentation.EnumTypeRepresentation representation);

}
