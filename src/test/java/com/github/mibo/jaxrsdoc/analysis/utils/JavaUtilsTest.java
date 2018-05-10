package com.github.mibo.jaxrsdoc.analysis.utils;

import com.github.mibo.jaxrsdoc.model.JavaUtils;
import com.github.mibo.jaxrsdoc.model.Types;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JavaUtilsTest {

    @Test
    public void testGetTypeParameters() {
        assertThat(JavaUtils.getTypeParameters("B").size(), is(0));
        assertThat(JavaUtils.getTypeParameters("Z").size(), is(0));
        assertThat(JavaUtils.getTypeParameters(Types.STRING).size(), is(0));
        assertThat(JavaUtils.getTypeParameters("[Ljava/lang/String;").size(), is(0));
        assertThat(JavaUtils.getTypeParameters("Ljava/util/List<Ljava/lang/String;>;"), hasItems(Types.STRING));
        assertThat(JavaUtils.getTypeParameters("Ljava/lang/Map<Ljava/lang/String;Ljava/lang/Integer;>;"), hasItems(Types.STRING, Types.INTEGER));
        assertThat(JavaUtils.getTypeParameters("Ljava/util/List<Ljava/util/List<Ljava/lang/Integer;>;>;"), hasItems("Ljava/util/List<Ljava/lang/Integer;>;"));
        assertThat(JavaUtils.getTypeParameters("Ljava/util/Map<Ljava/util/List<Ljava/lang/Integer;>;Ljava/util/List<Ljava/lang/String;>;>;"), hasItems("Ljava/util/List<Ljava/lang/Integer;>;", "Ljava/util/List<Ljava/lang/String;>;"));
    }

    @Test
    public void testIsAssignableToSame() {
        final String sameType = "Ljava/lang/Object;";
        assertTrue(JavaUtils.isAssignableTo(sameType, Types.OBJECT));
        assertTrue(JavaUtils.isAssignableTo(Types.OBJECT, sameType));
    }

    @Test
    public void testIsAssignableToPrimitives() {
        assertFalse(JavaUtils.isAssignableTo(Types.PRIMITIVE_INT, Types.OBJECT));
        assertFalse(JavaUtils.isAssignableTo(Types.OBJECT, Types.PRIMITIVE_INT));
        assertFalse(JavaUtils.isAssignableTo(Types.PRIMITIVE_BOOLEAN, Types.PRIMITIVE_INT));
        assertTrue(JavaUtils.isAssignableTo(Types.PRIMITIVE_INT, Types.PRIMITIVE_INT));
    }

    @Test
    public void testIsAssignableToArray() {
        final String intArray = "[I";
        final String objectArray = "[Ljava/lang/Object;";
        final String listArray = "[Ljava/util/List;";
        final String stringListArray = "[Ljava/util/List<Ljava/lang/String;>";
        final String linkedListArray = "[Ljava/util/LinkedList;";

        assertFalse(JavaUtils.isAssignableTo(intArray, Types.PRIMITIVE_INT));
        assertFalse(JavaUtils.isAssignableTo(Types.PRIMITIVE_INT, intArray));
        assertFalse(JavaUtils.isAssignableTo(objectArray, intArray));
        assertFalse(JavaUtils.isAssignableTo(intArray, objectArray));
        assertTrue(JavaUtils.isAssignableTo(objectArray, objectArray));
        assertTrue(JavaUtils.isAssignableTo(intArray, intArray));
        assertTrue(JavaUtils.isAssignableTo(listArray, stringListArray));
        assertTrue(JavaUtils.isAssignableTo(stringListArray, listArray));
        assertTrue(JavaUtils.isAssignableTo(linkedListArray, listArray));
        assertFalse(JavaUtils.isAssignableTo(listArray, linkedListArray));
    }

    @Test
    public void testIsAssignableToInheritance() {
        final String parentType = "Ljava/lang/Number;";
        final String inheritedType = "Ljava/lang/Integer;";
        assertFalse(JavaUtils.isAssignableTo(parentType, inheritedType));
        assertTrue(JavaUtils.isAssignableTo(inheritedType, parentType));
    }

    @Test
    public void testIsAssignableToGeneric() {
        final String parentType = "Ljava/util/List;";
        final String inheritedType = "Ljava/util/LinkedList;";
        assertFalse(JavaUtils.isAssignableTo(parentType, inheritedType));
        assertTrue(JavaUtils.isAssignableTo(inheritedType, parentType));
    }

    @Test
    public void testIsAssignableToParameterized() {
        final String parentType = "Ljava/util/List<Ljava/lang/String;>;";
        final String inheritedType = "Ljava/util/LinkedList<Ljava/lang/String;>;";
        assertFalse(JavaUtils.isAssignableTo(parentType, inheritedType));
        assertTrue(JavaUtils.isAssignableTo(inheritedType, parentType));
    }

    @Test
    public void testIsAssignableToDifferentTypes() {
        assertFalse(JavaUtils.isAssignableTo(Types.STRING, Types.STREAM));
        assertFalse(JavaUtils.isAssignableTo(Types.STREAM, Types.STRING));
    }

    @Test
    public void testIsAssignableToParameterizedInheritedParameters() {
        final String parentType = "Ljava/util/List<Ljava/lang/Number;>;";
        final String inheritedType = "Ljava/util/List<Ljava/lang/Integer;>;";
        assertFalse(JavaUtils.isAssignableTo(parentType, inheritedType));
        assertFalse(JavaUtils.isAssignableTo(inheritedType, parentType));
    }

    @Test
    public void testIsAssignableToParameterizedInheritedParametersExtended() {
        final String parentType = "Ljava/util/List<Ljava/lang/Number;>;";
        final String inheritedType = "Ljava/util/LinkedList<Ljava/lang/Integer;>;";
        assertFalse(JavaUtils.isAssignableTo(parentType, inheritedType));
        assertFalse(JavaUtils.isAssignableTo(inheritedType, parentType));
    }

    @Test
    public void testIsAssignableToCollection() {
        final String type = "Ljava/util/List<Ljava/lang/String;>;";
        assertFalse(JavaUtils.isAssignableTo(Types.COLLECTION, type));
        assertTrue(JavaUtils.isAssignableTo(type, Types.COLLECTION));
    }

    @Test
    public void testIsAssignableToOddParameterizedTypes() {
        final String type = "Ljava/util/List<Ljava/lang/String;>;";
        final String subType = "Ljava/util/LinkedList<Ljava/lang/String;>;";
        final String rawType = "Ljava/util/List;";
        final String rawSubType = "Ljava/util/LinkedList;";
        assertFalse(JavaUtils.isAssignableTo(type, subType));
        assertTrue(JavaUtils.isAssignableTo(subType, type));
        assertTrue(JavaUtils.isAssignableTo(type, rawType));
        assertTrue(JavaUtils.isAssignableTo(rawType, type));
        assertFalse(JavaUtils.isAssignableTo(type, rawSubType));
        assertTrue(JavaUtils.isAssignableTo(rawSubType, type));
        assertTrue(JavaUtils.isAssignableTo(subType, rawType));
        assertFalse(JavaUtils.isAssignableTo(rawType, subType));
        assertTrue(JavaUtils.isAssignableTo(subType, rawSubType));
        assertTrue(JavaUtils.isAssignableTo(rawSubType, subType));
        assertFalse(JavaUtils.isAssignableTo(rawType, rawSubType));
        assertTrue(JavaUtils.isAssignableTo(rawSubType, rawType));
    }

    @Test
    public void testDetermineMostSpecificTypeOnlyOne() {
        final String actualType = JavaUtils.determineMostSpecificType(Types.LIST);
        assertEquals(Types.LIST, actualType);
    }

    @Test
    public void testDetermineMostSpecificTypeParameterized() {
        final String parameterizedType = "Ljava/util/List<Ljava/lang/String;>;";

        String actualType = JavaUtils.determineMostSpecificType(Types.LIST, parameterizedType);
        assertEquals(parameterizedType, actualType);

        actualType = JavaUtils.determineMostSpecificType(parameterizedType, Types.LIST);
        assertEquals(parameterizedType, actualType);
    }

    @Test
    public void testDetermineMostSpecificTypeArray() {
        final String stringArray = "[Ljava/lang/String;";

        String actualType = JavaUtils.determineMostSpecificType(Types.STRING, stringArray);
        assertEquals(stringArray, actualType);

        actualType = JavaUtils.determineMostSpecificType(stringArray, Types.STRING);
        assertEquals(stringArray, actualType);
    }

    @Test
    public void testDetermineMostSpecificTypeObject() {
        final String responseType = "Ljavax/ws/rs/core/Response$Status;";

        String actualType = JavaUtils.determineMostSpecificType(Types.OBJECT, responseType);
        assertEquals(responseType, actualType);

        actualType = JavaUtils.determineMostSpecificType(responseType, Types.OBJECT);
        assertEquals(responseType, actualType);
    }

    @Test
    public void testDetermineMostSpecificTypeInheritance() {
        final String linkedListType = "Ljava/util/LinkedList;";

        String actualType = JavaUtils.determineMostSpecificType(Types.LIST, linkedListType);
        assertEquals(linkedListType, actualType);

        actualType = JavaUtils.determineMostSpecificType(linkedListType, Types.LIST);
        assertEquals(linkedListType, actualType);
    }

    @Test
    public void testDetermineMostSpecificTypeParameterizedInner() {
        final String lockList = "Ljava/util/List<Ljava/util/concurrent/locks/Lock;>;";
        final String stampedLockList = "Ljava/util/List<Ljava/util/concurrent/locks/ReentrantLock;>;";

        String actualType = JavaUtils.determineMostSpecificType(lockList, stampedLockList);
        assertEquals(stampedLockList, actualType);

        actualType = JavaUtils.determineMostSpecificType(stampedLockList, lockList);
        assertEquals(stampedLockList, actualType);
    }

    @Test
    public void testDetermineMostSpecificTypeParameterizedInheritance() {
        final String lockList = "Ljava/util/List<Ljava/util/concurrent/locks/Lock;>;";
        final String lockLinkedList = "Ljava/util/LinkedList<Ljava/util/concurrent/locks/Lock;>;";

        String actualType = JavaUtils.determineMostSpecificType(lockList, lockLinkedList);
        assertEquals(lockLinkedList, actualType);

        actualType = JavaUtils.determineMostSpecificType(lockLinkedList, lockList);
        assertEquals(lockLinkedList, actualType);
    }

    @Test
    public void testDetermineMostSpecificTypeParameterizedInnerInheritance() {
        final String lockList = "Ljava/util/List<Ljava/util/concurrent/locks/Lock;>;";
        final String stampedLockLinkedList = "Ljava/util/LinkedList<Ljava/util/concurrent/locks/ReentrantLock;>;";

        String actualType = JavaUtils.determineMostSpecificType(lockList, stampedLockLinkedList);
        assertEquals(stampedLockLinkedList, actualType);

        actualType = JavaUtils.determineMostSpecificType(stampedLockLinkedList, lockList);
        assertEquals(stampedLockLinkedList, actualType);
    }

}