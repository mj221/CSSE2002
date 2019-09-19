/**
 * An interface for a block.
 *
 * @author Minjae Lee
 */
public interface Block {
    java.lang.String getColour();
    java.lang.String getBlockType();
    boolean isDiggable();
    boolean isMoveable();
    boolean isCarryable();
    }
