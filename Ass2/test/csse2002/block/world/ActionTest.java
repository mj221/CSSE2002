package csse2002.block.world;

import csse2002.block.world.Action;
import csse2002.block.world.ActionFormatException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ActionTest {

    @Test
    /*Test if I can get the correct primary action*/
    public void getPrimaryAction() {
        Action action = new Action(0,"north");
        Integer primaryaction = action.getPrimaryAction();
        assertEquals(Integer.toString(primaryaction),"0");
    }

    @Test
    /*Test if I can get the correct secondary action*/
    public void getSecondaryAction() {
        Action action = new Action(0,null);
        String secondaryaction = action.getSecondaryAction();
        assertEquals(secondaryaction,"");
    }

    @Test
    /*Test if I can get the correct secondary action*/
    public void getSecondaryAction2() {
        Action action2 = new Action(0,"north");
        String secondaryaction = action2.getSecondaryAction();
        assertEquals(secondaryaction,"north");
    }

    @Test
    /*Test if I can correctly load an action*/
    public void loadAction() {
        Action action = new Action(2,"");
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader("action.txt"));
            try {
                action.loadAction(reader);
            } catch (ActionFormatException e) {
                fail();
            }
            assertEquals(action.loadAction(reader).getPrimaryAction(),new Action(2,"").getPrimaryAction());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ActionFormatException e) {
            fail();
        }
    }

}