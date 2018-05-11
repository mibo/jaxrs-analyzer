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

package com.github.mibo.jaxrsdoc.analysis.bytecode.collection.testclasses;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.instructions.*;

import java.util.LinkedList;
import java.util.List;

public class TestClass8 {

    public double method(final int number) {
        // force dup2 opcode use
        final double d1, d2;

        d1 = d2 = 2.0;

        return d1;
    }

    public static List<Instruction> getResult() {
        final List<Instruction> instructions = new LinkedList<>();

        // constant folding
        instructions.add(new PushInstruction(2.0, Types.DOUBLE, null));
        instructions.add(new DupInstruction(null));
        instructions.add(new StoreInstruction(4, Types.OBJECT, null));
        instructions.add(new StoreInstruction(2, Types.OBJECT, null));
        instructions.add(new LoadInstruction(2, Types.PRIMITIVE_DOUBLE, "d1", null, null));
        instructions.add(new ReturnInstruction(null));

        return instructions;
    }

}
