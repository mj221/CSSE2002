import org.junit.Test;
import static org.junit.Assert.*;

/**
 * jUNIT4 GrassBlock Test
 *
 * @author Minjae Lee
 */
public class GrassBlockTest {
    /**
     * Test if the colour of grass block is indeed 'green'.
     */
    @Test
    public void getColour() {
        GrassBlock grass = new GrassBlock();
        String colour = grass.getColour();
        assertEquals(colour, "green");
    }

    /**
     * Test if the type of grass block is indeed 'grass'.
     */
    @Test
    public void getBlockType(){
        GrassBlock grass = new GrassBlock();
        String Type = grass.getBlockType();
        assertEquals(Type, "grass");
    }

    /**
     * Test if the grass block is indeed carryable.
     */
    @Test
    public void isCarryable(){
        GrassBlock grass = new GrassBlock();
        Boolean Carryable = grass.isCarryable();
        assertFalse(Carryable);
    }

    /**
     * Test if the grass block is indeed diggable.
     */
    @Test
    public void isDiggable() {
        GrassBlock grass = new GrassBlock();
        Boolean Diggable = grass.isDiggable();
        assertTrue(Diggable);
    }

    /**
     * Test if the grass block is indeed moveable.
     */
    @Test
    public void isMoveable() {
        GrassBlock grass = new GrassBlock();
        Boolean Moveable = grass.isMoveable();
        assertFalse(Moveable);
    }
}