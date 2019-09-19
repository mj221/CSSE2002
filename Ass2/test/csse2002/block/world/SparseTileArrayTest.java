package csse2002.block.world;

import csse2002.block.world.*;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class SparseTileArrayTest {

    @Test
    /*checks there is no tile at x,y initially*/
    public void getTile() {
        SparseTileArray tiletest = new SparseTileArray();
        Tile tileresult = tiletest.getTile(new Position(0,1));
        assertEquals(tileresult,null);
    }

    @Test
    /*checks there is said tile at x,y initially*/
    public void getTile2() {
        Tile t1 = new Tile();
        java.util.List<Block> initialtile = new LinkedList<Block>(t1.getBlocks());
        SparseTileArray tiletest = new SparseTileArray();
        try {
            tiletest.addLinkedTiles(t1,0,1 );
        } catch (WorldMapInconsistentException e) {
            fail();
        }
        Tile tileresult = tiletest.getTile(new Position(0,1));
        assertEquals(tileresult.getBlocks(),initialtile);
    }

    @Test
    public void getTiles() {
        SparseTileArray tiletest = new SparseTileArray();
        SparseTileArray tiletest2 = new SparseTileArray();
        assertFalse(tiletest.getTiles().equals(tiletest2));

    }

}