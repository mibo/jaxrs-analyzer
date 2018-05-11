/*
 * Copyright (C) 2015 Sebastian Daschner, sebastian-daschner.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.mibo.jaxrsdoc.analysis.bytecode.reduction;

import com.github.mibo.jaxrsdoc.model.JavaUtils;
import com.github.mibo.jaxrsdoc.model.instructions.Instruction;
import com.github.mibo.jaxrsdoc.model.methods.MethodIdentifier;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;
import com.github.mibo.jaxrsdoc.analysis.classes.ContextClassReader;
import com.github.mibo.jaxrsdoc.analysis.classes.ProjectMethodClassVisitor;
import com.github.mibo.jaxrsdoc.analysis.utils.TestClassUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;

import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class RelevantInstructionReducerTest {

    private final RelevantInstructionReducer classUnderTest;
    private final String testClass;
    private final List<Instruction> expectedInstructions;

    public RelevantInstructionReducerTest(final String testClass, final List<Instruction> expectedInstructions) {
        this.testClass = testClass;
        this.expectedInstructions = expectedInstructions;
        this.classUnderTest = new RelevantInstructionReducer();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NotFoundException, IOException, ReflectiveOperationException {
        Collection<Object[]> data = new LinkedList<>();

        final Set<String> testClasses = TestClassUtils.getClasses("com/github/mibo/jaxrsdoc/analysis/bytecode/reduction/testclasses");

        testClassLabel:
        for (final String testClass : testClasses) {
            final Object[] testData = new Object[2];
            data.add(testData);

            testData[0] = testClass;
            final Class<?> loadedClass = JavaUtils.loadClassFromName(testClass);

            // take "instructions"-method with higher priority
            for (Method method : loadedClass.getDeclaredMethods()) {
                if ("instructions".equals(method.getName())) {
                    testData[1] = loadedClass.getDeclaredMethod("instructions").invoke(null);
                    continue testClassLabel;
                }
            }

            // "expected"#-methods
            testData[1] = new LinkedList<>();
            Stream.of(loadedClass.getDeclaredMethods())
                    .filter(m -> m.getName().startsWith("expected")).sorted(Comparator.comparing(Method::getName))
                    .map(RelevantInstructionReducerTest::getInstructions)
                    .forEach(((List<Instruction>) testData[1])::addAll);
        }

        return data;
    }

    @Test
    public void test() throws IOException {
        // round trip to find the correct test method
        final Method method = Stream.of(JavaUtils.loadClassFromName(testClass).getDeclaredMethods()).filter(m -> m.getName().equals("method")).findAny().orElseThrow(NoSuchElementException::new);

        final List<Instruction> visitedInstructions = classUnderTest.reduceInstructions(getInstructions(method));

        Assert.assertEquals("failed for " + testClass, expectedInstructions, visitedInstructions);
    }

    private static List<Instruction> getInstructions(final Method method) {
        try {
            final String className = method.getDeclaringClass().getCanonicalName().replace('.', '/');
            final MethodResult methodResult = new MethodResult();
            final ProjectMethodClassVisitor visitor = new ProjectMethodClassVisitor(methodResult, MethodIdentifier.of(className, method.getName(), Type.getMethodDescriptor(method), false));
            new ContextClassReader(className).accept(visitor, ClassReader.EXPAND_FRAMES);

            return methodResult.getInstructions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
