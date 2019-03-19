package hoodbox.interpreter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import hoodbox.HoodBox;

public class InterpreterCompositeTests
{
    private HoodBox box = new HoodBox();
    private Interpreter i = new Interpreter(box);

    @Test
    public void setWriteRead() {
        i.SET(0, 50);
        i.WRITE(0, 1024);
        i.READ(1024, 1);

        assertEquals(50, box.readRegister((short) 1));
    }

    @Test
    public void setEqGt() {
        i.SET(0, 200);
        i.SET(1, 100);
        i.SET(2, 50);
        i.SET(3, 25);
        i.SET(4, 50);
        i.SET(5, 100);

        i.EQ(0, 0, 6); // Equality should be reflexive.
        assertEquals(0xffffff, box.readRegister((short) 6));
        i.GT(0, 0, 7); // Greater-than should NOT be reflexive.
        assertEquals(0, box.readRegister((short) 7));

        // maybe a useful property; TRUE is greater than false
        i.GT(6, 7, 0);
        assertEquals(0xffffff, box.readRegister((short) 0));
    }
}
