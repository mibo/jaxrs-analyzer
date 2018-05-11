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

package com.github.mibo.jaxrsdoc.analysis.results;

import com.github.mibo.jaxrsdoc.model.JavaUtils;
import com.github.mibo.jaxrsdoc.model.Types;

import javax.ws.rs.core.GenericEntity;

/**
 * Normalizes the request/response body Java types.
 *
 * @author Sebastian Daschner
 */
final class ResponseTypeNormalizer {

    private ResponseTypeNormalizer() {
        throw new UnsupportedOperationException();
    }

    /**
     * Normalizes the contained collection type.
     *
     * @param type The type
     * @return The normalized type
     */
    static String normalizeCollection(final String type) {
        if (JavaUtils.isAssignableTo(type, Types.COLLECTION)) {
            if (!JavaUtils.getTypeParameters(type).isEmpty()) {
                return JavaUtils.getTypeParameters(type).get(0);
            }
            return Types.OBJECT;
        }
        return type;
    }

    /**
     * Normalizes the body type (e.g. removes nested {@link GenericEntity}s).
     *
     * @param type The type
     * @return The normalized type
     */
    static String normalizeResponseWrapper(final String type) {
        if (!JavaUtils.getTypeParameters(type).isEmpty() && JavaUtils.isAssignableTo(type, Types.GENERIC_ENTITY))
            return JavaUtils.getTypeParameters(type).get(0);
        return type;
    }

}
