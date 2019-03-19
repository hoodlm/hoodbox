package hoodbox;

import static org.junit.Assert.*;

import org.junit.Test;

public class HoodBoxTest
{
    private static final int MEMORY_ADDRESS_OVERFLOW = 16777216;

    private HoodBox box = new HoodBox();

    @Test
    public void setAndReadRegistersPositive()
    {
        for (short registerIndex = 0; registerIndex < 8; ++registerIndex) {
            final int setValue = 2 + (int) registerIndex;
            box.setRegister(registerIndex, setValue);
            assertEquals(setValue, box.readRegister(registerIndex));
        }
    }

    @Test
    public void setAndReadMemoryPositive()
    {
        for (int address = 0; address < 16777216; address += 107) {
            final short setValue = (short) ((1 + address) % 20);
            box.setMemory(address, setValue);
            assertEquals(setValue, box.readMemory(address));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMemoryNegativeValueThrowsIllegalArgument() {
        box.setMemory(100000, (short) -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMemoryValueTooLargeThrowsIllegalArgument() {
        box.setMemory(100000, (short) 257);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMemoryNegativeAddressThrowsIllegalArgument() {
        box.setMemory(-1, (short) 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMemoryTooLargeAddressThrowsIllegalArgument() {
        box.setMemory(MEMORY_ADDRESS_OVERFLOW, (short) 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRegisterNegativeValueThrowsIllegalArgument() {
        box.setRegister((short) 3, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRegisterValueTooLargeThrowsIllegalArgument() {
        box.setRegister((short) 3, MEMORY_ADDRESS_OVERFLOW);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRegisterNegativeAddressThrowsIllegalArgument() {
        box.setRegister((short) -1, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRegisterTooLargeAddressThrowsIllegalArgument() {
        box.setRegister((short) 8, 100);
    }
}
