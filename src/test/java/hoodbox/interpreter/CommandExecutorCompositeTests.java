package hoodbox.interpreter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import hoodbox.HoodBox;

public class CommandExecutorCompositeTests
{
    private HoodBox box = new HoodBox();
    private CommandExecutor x = new CommandExecutor(box);

    @Test
    public void setWriteRead() {
        x.SET(0, 50);
        x.WRITE(0, 1024);
        x.READ(1024, 1);

        assertEquals(50, box.readRegister((short) 1));
    }

    @Test
    public void setEqGt() {
        x.SET(0, 200);
        x.SET(1, 100);
        x.SET(2, 50);
        x.SET(3, 25);
        x.SET(4, 50);
        x.SET(5, 100);

        x.EQ(0, 0, 6); // Equality should be reflexive.
        assertEquals(0xffffff, box.readRegister((short) 6));
        x.GT(0, 0, 7); // Greater-than should NOT be reflexive.
        assertEquals(0, box.readRegister((short) 7));

        // maybe a useful property; TRUE is greater than false
        x.GT(6, 7, 0);
        assertEquals(0xffffff, box.readRegister((short) 0));
    }
}
