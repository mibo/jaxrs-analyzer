package com.github.mibo.jaxrsdoc.backend;

import static com.github.mibo.jaxrsdoc.model.Types.BOOLEAN;
import static com.github.mibo.jaxrsdoc.model.Types.DOUBLE_TYPES;
import static com.github.mibo.jaxrsdoc.model.Types.INTEGER_TYPES;
import static com.github.mibo.jaxrsdoc.model.Types.PRIMITIVE_BOOLEAN;
import static com.github.mibo.jaxrsdoc.model.Types.STRING;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.mibo.jaxrsdoc.model.rest.TypeIdentifier;
import com.github.mibo.jaxrsdoc.model.rest.TypeRepresentation;
import com.github.mibo.jaxrsdoc.model.rest.TypeRepresentationVisitor;

/**
 * Adds the JSON representation of type identifiers to String builders.
 *
 * @author Sebastian Daschner
 */
class JsonRepresentationAppender implements TypeRepresentationVisitor {

    private final StringBuilder builder;
    private final Map<TypeIdentifier, TypeRepresentation> representations;

    private Set<TypeIdentifier> visitedTypes = new HashSet<>();

    JsonRepresentationAppender(final StringBuilder builder, final Map<TypeIdentifier, TypeRepresentation> representations) {
        this.builder = builder;
        this.representations = representations;
    }

    @Override
    public void visit(TypeRepresentation.ConcreteTypeRepresentation representation) {
        if (representation.getProperties().isEmpty())
            builder.append(toPrimitiveType(representation.getIdentifier()));
        else {
            builder.append('{');
            visitedTypes.add(representation.getIdentifier());
            representation.getProperties().entrySet().stream().sorted(ComparatorUtils.mapKeyComparator()).forEach(e -> {
                builder.append('"').append(e.getKey()).append("\":");
                final TypeRepresentation nestedRepresentation = representations.get(e.getValue());
                if (nestedRepresentation == null)
                    builder.append(toPrimitiveType(e.getValue()));
                else if (visitedTypes.contains(e.getValue()))
                    // prevent infinite loop from recursively nested types
                    builder.append("{}");
                else
                    nestedRepresentation.accept(this);
                builder.append(',');
            });
            visitedTypes.remove(representation.getIdentifier());
            builder.deleteCharAt(builder.length() - 1).append('}');
        }
    }

    @Override
    public void visitStart(TypeRepresentation.CollectionTypeRepresentation representation) {
        builder.append('[');
    }

    @Override
    public void visitEnd(TypeRepresentation.CollectionTypeRepresentation representation) {
        builder.append(']');
    }

    @Override
    public void visit(final TypeRepresentation.EnumTypeRepresentation representation) {
        final String values = '"' + representation.getEnumValues().stream().sorted()
                .collect(Collectors.joining("|")) + '"';

        builder.append(values.length() == 2 ? "\"string\"" : values);
    }

    private static String toPrimitiveType(final TypeIdentifier value) {
        final String type = value.getType();
        if (STRING.equals(type))
            return "\"string\"";

        if (BOOLEAN.equals(type) || PRIMITIVE_BOOLEAN.equals(type))
            return "false";

        if (INTEGER_TYPES.contains(type))
            return "0";

        if (DOUBLE_TYPES.contains(type))
            return "0.0";

        return "{}";
    }
}
