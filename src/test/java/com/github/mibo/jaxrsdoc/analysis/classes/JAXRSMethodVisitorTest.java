package com.github.mibo.jaxrsdoc.analysis.classes;

import com.github.mibo.jaxrsdoc.model.instructions.Instruction;
import com.github.mibo.jaxrsdoc.model.methods.MethodIdentifier;
import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JAXRSMethodVisitorTest {

    private JAXRSMethodVisitor cut;
    private ClassResult classResult;

    @Before
    public void setUp() {
        classResult = new ClassResult();
        final MethodIdentifier identifier = MethodIdentifier.of("Foobar", "foo", "()V", false);
        cut = new JAXRSMethodVisitor(identifier, classResult, new MethodResult(), true);
    }

    @Test
    public void test() {
        final Label start = new Label();
        final Label end = new Label();

        cut.visitLabel(new Label());
        cut.visitIntInsn(Opcodes.BIPUSH, 2);
        cut.visitLabel(start);
        cut.visitVarInsn(Opcodes.ISTORE, 1);
        cut.visitInsn(Opcodes.NOP);
        cut.visitLabel(new Label());
        cut.visitVarInsn(Opcodes.ILOAD, 1);
        cut.visitLabel(end);

        final List<Instruction> instructions = classResult.getMethods().iterator().next().getInstructions();
        assertThat(instructions.size(), is(4));

        cut.visitLocalVariable("foobar", "Ljava/lang/String;", null, start, end, 1);

        assertThat(instructions.size(), is(4));
        assertThat(instructions.stream().filter(i -> i.getType() == Instruction.InstructionType.LOAD).count(), is(1L));
        assertThat(instructions.stream().filter(i -> i.getType() == Instruction.InstructionType.STORE).count(), is(1L));
    }

}