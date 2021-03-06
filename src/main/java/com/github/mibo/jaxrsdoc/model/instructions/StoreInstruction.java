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

package com.github.mibo.jaxrsdoc.model.instructions;

import org.objectweb.asm.Label;

/**
 * Represents a XZY_STORE_X instruction (for any number).
 *
 * @author Sebastian Daschner
 */
public class StoreInstruction extends LoadStoreInstruction {

    public StoreInstruction(final int number, final String variableType, final Label label) {
        super(number, variableType, label);
    }

    public StoreInstruction(final int number, final String variableType, final String name, final Label label) {
        super(number, variableType, name, label);
    }

    @Override
    public int getStackSizeDifference() {
        return -1;
    }

    @Override
    public InstructionType getType() {
        return InstructionType.STORE;
    }

}
