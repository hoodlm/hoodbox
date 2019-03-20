package hoodbox.interpreter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import hoodbox.HoodBox;

public class CommandExecutorMultibyteReadWriteTests
{
    private HoodBox box = new HoodBox();
    private CommandExecutor executor = new CommandExecutor(box);

    @Test
    public void readTwo16() {
        box.setMemory(1024, (short) 0);
        box.setMemory(1025, (short) 16);

        executor.READTWO(1024, 0);
        assertEquals(16, box.readRegister((short) 0));
    }

    @Test
    public void readTwo256() {
        box.setMemory(1024, (short) 1);
        box.setMemory(1025, (short) 0);

        executor.READTWO(1024, 0);
        assertEquals(256, box.readRegister((short) 0));
    }

    @Test
    public void readTwo513() {
        box.setMemory(1024, (short) 2);
        box.setMemory(1025, (short) 1);

        executor.READTWO(1024, 1);
        assertEquals(513, box.readRegister((short) 1));
    }

    @Test
    public void readThree0() {
        box.setMemory(1024, (short) 0);
        box.setMemory(1025, (short) 0);
        box.setMemory(1026, (short) 0);

        executor.READTHREE(1024, 0);
        assertEquals(0, box.readRegister((short) 0));
    }

    @Test
    public void readThree15() {
        box.setMemory(1024, (short) 0);
        box.setMemory(1025, (short) 0);
        box.setMemory(1026, (short) 15);

        executor.READTHREE(1024, 0);
        assertEquals(15, box.readRegister((short) 0));
    }

    @Test
    public void readThree514() {
        box.setMemory(1024, (short) 0);
        box.setMemory(1025, (short) 2);
        box.setMemory(1026, (short) 2);

        executor.READTHREE(1024, 0);
        assertEquals(514, box.readRegister((short) 0));
    }

    @Test
    // 131072 == 256*256
    public void readThree131072() {
        box.setMemory(1024, (short) 1);
        box.setMemory(1025, (short) 0);
        box.setMemory(1026, (short) 0);

        executor.READTHREE(1024, 0);
        assertEquals(65536, box.readRegister((short) 0));
    }

    @Test
    //5255752 == 80*256*256 + 50*256 + 72
    public void readThree5255752() {
        box.setMemory(1024, (short) 80);
        box.setMemory(1025, (short) 50);
        box.setMemory(1026, (short) 72);

        executor.READTHREE(1024, 0);
        assertEquals(5255752, box.readRegister((short) 0));
    }

    @Test
    // 15777215 == 2^24 -- 1
    public void readThree15777215() {
        box.setMemory(1024, (short) 255);
        box.setMemory(1025, (short) 255);
        box.setMemory(1026, (short) 255);

        executor.READTHREE(1024, 0);
        assertEquals(16777215, box.readRegister((short) 0));
    }

    @Test
    public void writeTwo16() {
        box.setRegister((short) 0, 16);

        executor.WRITETWO(0, 1024);
        assertEquals(0,  box.readMemory(1024));
        assertEquals(16, box.readMemory(1025));
    }

    @Test
    public void writeTwo512() {
        box.setRegister((short) 0, 512);

        executor.WRITETWO(0, 1024);
        assertEquals(2, box.readMemory(1024));
        assertEquals(0, box.readMemory(1025));
    }

    @Test
    public void writeTwoTruncates() {
        // 262726 == 4 * 256 * 256 + 2 * 256 + 70
        //                           2 * 256 + 70 == 582
        box.setRegister((short) 0, 262726);

        executor.WRITETWO(0, 30000);
        assertEquals(2, box.readMemory(30000));
        assertEquals(70, box.readMemory(30001));
    }

    @Test
    public void writeTwo1025() {
        box.setRegister((short) 0, 1025);

        executor.WRITETWO(0, 1024);
        assertEquals(4, box.readMemory(1024));
        assertEquals(1, box.readMemory(1025));
    }

    @Test
    public void writeThree16() {
        box.setRegister((short) 0, 16);

        executor.WRITETHREE(0, 1024);
        assertEquals(0,  box.readMemory(1024));
        assertEquals(0,  box.readMemory(1025));
        assertEquals(16, box.readMemory(1026));
    }

    @Test
    public void writeThree256() {
        box.setRegister((short) 0, 256);

        executor.WRITETHREE(0, 1024);
        assertEquals(0, box.readMemory(1024));
        assertEquals(1, box.readMemory(1025));
        assertEquals(0, box.readMemory(1026));
    }

    @Test
    public void writeThree513() {
        box.setRegister((short) 0, 513);

        executor.WRITETHREE(0, 1024);
        assertEquals(0, box.readMemory(1024));
        assertEquals(2, box.readMemory(1025));
        assertEquals(1, box.readMemory(1026));
    }

    @Test
    public void writeThree65536() {
        box.setRegister((short) 0, 65536);

        executor.WRITETHREE(0, 1024);
        assertEquals(1, box.readMemory(1024));
        assertEquals(0, box.readMemory(1025));
        assertEquals(0, box.readMemory(1026));
    }

    @Test
    public void writeThree65537() {
        box.setRegister((short) 0, 65537);

        executor.WRITETHREE(0, 1024);
        assertEquals(1, box.readMemory(1024));
        assertEquals(0, box.readMemory(1025));
        assertEquals(1, box.readMemory(1026));
    }

    @Test
    public void writeThree16777215() {
        box.setRegister((short) 0, 16777215);

        executor.WRITETHREE(0, 1024);
        assertEquals(255, box.readMemory(1024));
        assertEquals(255, box.readMemory(1025));
        assertEquals(255, box.readMemory(1026));
    }
}
