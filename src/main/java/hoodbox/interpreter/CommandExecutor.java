package hoodbox.interpreter;

import hoodbox.HoodBox;
import lombok.AllArgsConstructor;

@AllArgsConstructor
/**
 * Enacts commands against the java HoodBox implementation.
 */
public class CommandExecutor {
    private static final int TRUE  = 0xffffff;
    private static final int FALSE = 0x0;
    private final HoodBox box;

    public void READ(final int memory, final int register) {
        final short value = box.readMemory(memory);
        box.setRegister((short) register, value);
    }

    public void READTWO(final int memory, final int register) {
        final int upper = box.readMemory(memory);
        final int lower = box.readMemory(memory+1);
        final int value = 256 * upper + lower;
        box.setRegister((short) register, value);
    }

    public void READTHREE(final int memory, final int register) {
        final int upper  = (int) box.readMemory(memory);
        final int middle = (int) box.readMemory(memory+1);
        final int lower  = (int) box.readMemory(memory+2);
        final int value = 256 * 256 * upper + 256 * middle + lower;
        box.setRegister((short) register, value);
    }

    public void WRITE(final int register, final int memory) {
        final int value = box.readRegister((short) register);
        final short truncatedValue = (short) (value % 256);
        box.setMemory(memory, truncatedValue);
    }

    public void WRITETWO(final int register, final int memory) {
        final int value = box.readRegister((short) register);
        final int lower  = value % 256;
        final int middle = ((value - lower) / 256) % 256;
        box.setMemory(memory, (short) middle);
        box.setMemory(memory+1, (short) lower);
    }

    public void WRITETHREE(final int register, final int memory) {
        final int value = box.readRegister((short) register);
        final int lower  = value % 256;
        final int middle = ((value - lower) / 256) % 256;
        final int upper  = (value - (middle + lower)) / (256 * 256);
        box.setMemory(memory, (short) upper);
        box.setMemory(memory+1, (short) middle);
        box.setMemory(memory+2, (short) lower);
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
