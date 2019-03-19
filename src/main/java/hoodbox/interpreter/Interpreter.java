package hoodbox.interpreter;

import hoodbox.HoodBox;
import lombok.AllArgsConstructor;

@AllArgsConstructor
/**
 * Enacts commands against the java HoodBox implementation.
 */
public class Interpreter {
    private static final int TRUE  = 0xffffff;
    private static final int FALSE = 0x0;
    private final HoodBox box;

    public void READ(final int memory, final int register) {
        final short value = box.readMemory(memory);
        box.setRegister((short) register, value);
    }

    public void WRITE(final int register, final int memory) {
        final int value = box.readRegister((short) register);
        final short truncatedValue = (short) (value % 256);
        box.setMemory(memory, truncatedValue);
    }

    public void SET(final int register, final int value) {
        box.setRegister((short) register, value);
    }

    public void EQ(final int register1, final int register2, final int destRegister) {
        final int value1 = box.readRegister((short) register1);
        final int value2 = box.readRegister((short) register2);

        final int setValue = (value1 == value2) ? TRUE : FALSE;
        box.setRegister((short) destRegister, setValue);
    }

    public void GT(final int register1, final int register2, final int destRegister) {
        final int value1 = box.readRegister((short) register1);
        final int value2 = box.readRegister((short) register2);

        final int setValue = (value1 > value2) ? TRUE : FALSE;
        box.setRegister((short) destRegister, setValue);
    }
}
