package com.github.mibo.jaxrsdoc.analysis.results;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.rest.TypeIdentifier;
import com.github.mibo.jaxrsdoc.model.rest.TypeRepresentation;

public final class TypeUtils {

    public static final TypeIdentifier OBJECT_IDENTIFIER = TypeIdentifier.ofType(Types.OBJECT);
    public static final TypeIdentifier STRING_IDENTIFIER = TypeIdentifier.ofType(Types.STRING);
    public static final TypeIdentifier INT_IDENTIFIER = TypeIdentifier.ofType(Types.PRIMITIVE_INT);
    public static final TypeIdentifier STRING_LIST_IDENTIFIER = TypeIdentifier.ofType("Ljava/util/List<Ljava/lang/String;>;");
    public static final TypeIdentifier MODEL_IDENTIFIER = TypeIdentifier.ofType("Lcom/github/mibo/test/Model;");

    private TypeUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the first representation fully equals to the second, i.e. the identifier has to be the same as well as the contained element
     * (for collections) or the properties (for concrete types).
     */
    public static boolean equals(final TypeRepresentation first, final TypeRepresentation second) {
        if (!first.equals(second))
            return false;

        final boolean firstCollection = first instanceof TypeRepresentation.CollectionTypeRepresentation;
        final boolean secondCollection = second instanceof TypeRepresentation.CollectionTypeRepresentation;

        if (firstCollection ^ secondCollection)
            return false;

        if (firstCollection)
            return ((TypeRepresentation.CollectionTypeRepresentation) first).contentEquals(((TypeRepresentation.CollectionTypeRepresentation) second).getRepresentation());

        final boolean firstEnum = first instanceof TypeRepresentation.EnumTypeRepresentation;
        final boolean secondEnum = second instanceof TypeRepresentation.EnumTypeRepresentation;

        if (firstEnum ^ secondEnum)
            return false;

        if (firstEnum) {
            final TypeRepresentation.EnumTypeRepresentation firstEnumRep = (TypeRepresentation.EnumTypeRepresentation) first;
            final TypeRepresentation.EnumTypeRepresentation secondEnumRep = (TypeRepresentation.EnumTypeRepresentation) second;
            return firstEnumRep.getEnumValues().equals(secondEnumRep.getEnumValues()) && firstEnumRep.getComponentType().equals(secondEnumRep.getComponentType());
        }

        return ((TypeRepresentation.ConcreteTypeRepresentation) first).contentEquals(((TypeRepresentation.ConcreteTypeRepresentation) second).getProperties());
    }

}
