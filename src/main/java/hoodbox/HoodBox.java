package hoodbox;

import lombok.NoArgsConstructor;

@NoArgsConstructor
/**
 * An interface to a simulated HoodBox VM.
 */
public class HoodBox {
    private static final int TOTAL_MEMORY_ADDRESSES = 16777216; // 2^24
    private static final short MAX_MEMORY_VALUE = 256;

    private static final short TOTAL_REGISTERS = 8;
    private static final int MAX_REGISTER_VALUE = 16777216; // also 2^24

    private final short[] memory = new short[TOTAL_MEMORY_ADDRESSES];
    private final int[] registers = new int[TOTAL_REGISTERS];

    public void setMemory(final int address, final short value) {
        checkMemoryAddress(address);
        checkMemoryValue(value);
        memory[address] = value;
    }

    public short readMemory(final int address) {
        checkMemoryAddress(address);
        return memory[address];
    }

    public void setRegister(final short register, final int value) {
        checkRegisterAddress(register);
        checkRegisterValue(value);
        registers[register] = value;
    }

    public int readRegister(final short register) {
        checkRegisterAddress(register);
        return registers[register];
    }

    private void checkMemoryAddress(final int address) {
        if (address < 0 || address >= TOTAL_MEMORY_ADDRESSES) {
            throw new IllegalArgumentException("Memory Address out of range: " + address);
        }
    }

    private void checkRegisterAddress(final short address) {
        if (address < 0 || address >= TOTAL_REGISTERS) {
            throw new IllegalArgumentException("Register Address out of range: " + address);
        }
    }

    private void checkMemoryValue(final short value) {
        if (value < 0 || value >= MAX_MEMORY_VALUE) {
            throw new IllegalArgumentException("Memory Value out of range: " + value);
        }
    }

    private void checkRegisterValue(final int value) {
        if (value < 0 || value >= MAX_REGISTER_VALUE) {
            throw new IllegalArgumentException("Register Value out of range: " + value);
        }
    }
}
