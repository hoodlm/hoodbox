package hoodbox.interpreter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import hoodbox.HoodBox;

public class InterpreterBasicTests
{
    private HoodBox box = new HoodBox();
    private Interpreter interpreter = new Interpreter(box);

    @Test
    public void read() {
        box.setRegister((short) 0, 30);
        box.setMemory(1024, (short) 42);

        interpreter.READ(1024, 0);
        assertEquals(42, box.readRegister((short) 0));
    }

    @Test
    public void write() {
        box.setRegister((short) 0, 30);
        box.setMemory(1024, (short) 42);

        interpreter.WRITE(0, 1024);
        assertEquals(30, box.readMemory(1024));
    }

    @Test
    public void set() {
        interpreter.SET(3, 512);
        assertEquals(512, box.readRegister((short) 3));
    }

    @Test
    public void eqTrue() {
        box.setRegister((short) 5, 30);
        box.setRegister((short) 6, 30);

        interpreter.EQ(5, 6, 0);

        assertEquals(0xffffff, box.readRegister((short) 0));
    }

    @Test
    public void eqFalse() {
        box.setRegister((short) 3, 30);
        box.setRegister((short) 4, 19);

        interpreter.EQ(3, 4, 5);

        assertEquals(0, box.readRegister((short) 5));
    }

    @Test
    public void gtTrue() {
        box.setRegister((short) 5, 31);
        box.setRegister((short) 6, 30);

        interpreter.GT(5, 6, 1);

        assertEquals(0xffffff, box.readRegister((short) 1));
    }

    @Test
    public void gtButActuallyEqual() {
        box.setRegister((short) 0, 5);
        box.setRegister((short) 1, 5);

        interpreter.GT(0, 1, 3);

        assertEquals(0, box.readRegister((short) 3));
    }

    @Test
    public void gtButActuallyLessThan() {
        box.setRegister((short) 1, 4);
        box.setRegister((short) 2, 5);

        interpreter.GT(1, 2, 4);

        assertEquals(0, box.readRegister((short) 4));
    }
}
