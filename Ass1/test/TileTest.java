import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * jUNIT4 Tile Test
 *
 * @author Minjae Lee
 */

public class TileTest {

    //Tests tile has no exits initially.
    @Test
    public void getExits(){
        Tile t1 = new Tile();
        assertEquals(0,t1.getExits().size());
    }

    //Tests if each tile is constructed with 3 initial blocks (Soil, Soil, Grass).
    @Test
    public void getBlocks(){
        Tile t1 = new Tile();
        assertEquals(3, t1.getBlocks().size());
    }

    //Gets the top block on the tile.
    @Test
    public void getTopBlock(){
        Tile t1 = new Tile();
        List<Block> initial = new ArrayList<>(t1.getBlocks());
        try {
            t1.getTopBlock();
        } catch (TooLowException e) {
            fail();
        }
        try {
            assertEquals(initial.get(t1.getBlocks().size()-1),t1.getTopBlock());
        } catch (TooLowException e) {
            e.printStackTrace();
        }
    }

    //Catch TooLowException if there are no blocks to get on the tile.
    @Test
    public void getTopBlock2() {
        Tile t2 = new Tile();
        try {
            for (int i =0; i<4; i++){
                t2.removeTopBlock();
            }
            fail();
        } catch (TooLowException e) {}
    }

    //Check for exception when there are no blocks on the tile to remove.
    @Test
    public void removeTopBlock() {
        Tile t2 = new Tile();
        try {
            for (int i =0; i<4; i++){
                t2.removeTopBlock();
            }
            fail();
        } catch (TooLowException e) {}
    }

    //Removes the top block on the tile.
    @Test
    public void removeTopBlock2(){
        Tile t1 = new Tile();
        List<Block> initial = new ArrayList<>(t1.getBlocks());
        initial.remove(t1.getBlocks().size()-1);
        try {
            t1.removeTopBlock();
        } catch (TooLowException e) {
            fail();
        }
        assertEquals(initial,t1.getBlocks());
    }
    //Checks if the method throws NoExitException when the name or target is null.
    @Test
    public void addExit() {
        Tile t2 = new Tile();
        Tile target1 = null;
        String name1 = "test";
        try {
            t2.addExit(name1,target1);
        } catch (NoExitException e) {
            fail();
        }
    }

    //Test if the new exit is added to the tile.
    @Test
    public void addExit2() {
        Tile t2 = new Tile();
        Tile target2 = t2;
        String name2 = "test2";
        try {
            t2.addExit(name2,target2);
        } catch (NoExitException e) {
            fail();
        }
    }

    //Tests if the exit is successfully removed from the tile.
    @Test
    public void removeExit() {
        Tile t2 = new Tile();
        Tile target1 = t2;
        String name1 = "test";
        try {
            t2.addExit(name1, target1);
        } catch (NoExitException e) {
            e.printStackTrace();
        }
        java.util.Map<java.lang.String,Tile> initial = new HashMap<>(t2.getExits());
        initial.remove("test");
        try {
            t2.removeExit(name1);
        } catch (NoExitException e) {
            fail();
        }
        assertEquals(initial,t2.getExits());
    }

    //Tests if a NoExitException is raised when there are no exit to remove.
    @Test
    public void removeExit2() {
        Tile t2 = new Tile();
        String name2 = null;
        try {
            t2.removeExit(name2);
            fail();
        } catch (NoExitException e) {}
    }

    //Attempt to dig in the current tile and checks if it was successfully dug.
    @Test
    public void dig() {
        Tile t1 = new Tile();
        List<Block> initial = new ArrayList<>(t1.getBlocks());

        try {
            initial.remove(initial.size()-1);
            t1.dig();
        } catch (TooLowException | InvalidBlockException e) {
            fail();
        }
        assertEquals(initial,t1.getBlocks());
    }

    //Tests if TooLowException is raised when there are no blocks to dig.
    @Test
    public void dig2() {
        Tile t2 = new Tile();
        try {
            for (int i = 0; i<4;i++){
                t2.dig();
            }
            fail();
        } catch (TooLowException | InvalidBlockException e) {}
    }

    //Tests if InvalidBlockException is raised when the top block is not a diggable block.
    @Test
    public void dig3() {
        Tile t3 = new Tile();
        t3.getBlocks().add(new StoneBlock());
        try {
            t3.dig();
            fail();
        } catch (TooLowException | InvalidBlockException e) {}
    }

    //Tests for NoExitException for when the exit is null or does not exist.
    @Test
    public void moveBlock() {
        Tile t2 = new Tile();
        String test1 = null ;
        try {
            t2.moveBlock(test1);
            fail();
        } catch (NoExitException | InvalidBlockException | TooHighException e) {}
    }

    //Tests for InvalidBlockException for when the block is null.
    @Test
    public void placeBlock() {
        Tile t2 = new Tile();
        Block block1 = null;
        try {
            t2.placeBlock(block1);
            fail();
        } catch (TooHighException | InvalidBlockException e) {}
    }
}