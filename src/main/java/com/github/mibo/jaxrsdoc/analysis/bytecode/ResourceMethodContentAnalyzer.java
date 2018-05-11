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

package com.github.mibo.jaxrsdoc.analysis.bytecode;

import com.github.mibo.jaxrsdoc.analysis.bytecode.simulation.MethodPool;
import com.github.mibo.jaxrsdoc.analysis.bytecode.simulation.MethodSimulator;
import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.elements.Element;
import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;
import com.github.mibo.jaxrsdoc.model.elements.JsonValue;
import com.github.mibo.jaxrsdoc.model.instructions.Instruction;
import com.github.mibo.jaxrsdoc.model.methods.ProjectMethod;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;

import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Analyzes JAX-RS resource methods. This class is thread-safe.
 *
 * @author Sebastian Daschner
 */
class ResourceMethodContentAnalyzer extends MethodContentAnalyzer {

    private final Lock lock = new ReentrantLock();

    /**
     * Analyzes the method (including own project methods).
     *
     * @param methodResult The method result
     */
    void analyze(final MethodResult methodResult) {
        lock.lock();
        try {
            buildPackagePrefix(methodResult.getParentResource().getOriginalClass());

            final List<Instruction> visitedInstructions = interpretRelevantInstructions(methodResult.getInstructions());

            // find project defined methods in invoke occurrences
            final Set<ProjectMethod> projectMethods = findProjectMethods(visitedInstructions);

            // add project methods to global method pool
            projectMethods.forEach(MethodPool.getInstance()::addProjectMethod);

            Element returnedElement = new MethodSimulator().simulate(visitedInstructions);
            final String returnType = methodResult.getOriginalMethodSignature().getReturnType();

            // void resource methods are interpreted later; stop analyzing on error
            if (Types.PRIMITIVE_VOID.equals(returnType)) {
                return;
            }

            if (returnedElement == null) {
                // happens for abstract methods or if there is no return
                return;
            }

            final Set<Object> possibleObjects = returnedElement.getPossibleValues().stream().filter(o -> !(o instanceof HttpResponse))
                    .collect(Collectors.toSet());

            // for non-Response methods add a default if there are non-Response objects or none objects at all
            if (!Types.RESPONSE.equals(returnType)) {
                final HttpResponse defaultResponse = new HttpResponse();

                if (Types.OBJECT.equals(returnType))
                    defaultResponse.getEntityTypes().addAll(returnedElement.getTypes());
                else
                    defaultResponse.getEntityTypes().add(returnType);

                possibleObjects.stream().filter(o -> o instanceof JsonValue).map(o -> (JsonValue) o).forEach(defaultResponse.getInlineEntities()::add);

                defaultResponse.getContentTypes().addAll(methodResult.getResponseMediaTypes());

                methodResult.getResponses().add(defaultResponse);
            }

            // add Response results as well
            returnedElement.getPossibleValues().stream().filter(o -> o instanceof HttpResponse).map(o -> (HttpResponse) o).forEach(methodResult.getResponses()::add);
        } finally {
            lock.unlock();
        }
    }

}
