/**
 * A stone block.
 *
 * @author Minjae Lee
 */
public class StoneBlock extends java.lang.Object implements Block {
    /**
     * StoneBlock constructor.
     */
    public StoneBlock(){}

    /**
     * Determine the colour of stone block.
     * @return Stone block is always 'gray'.
     */
    @Override
    public String getColour() {
        return "gray";
    }

    /**
     * Determine the type of stone block.
     * @return Stone block is a always a 'stone'.
     */
    @Override
    public String getBlockType() {
        return "stone";
    }

    /**
     * Determine if the stone block is diggable.
     * @return Stone block is not diggable.
     */
    @Override
    public boolean isDiggable() {
        return false;
    }

    /**
     * Determine if the stone block is moveable.
     * @return Stone block is not moveable.
     */
    @Override
    public boolean isMoveable() {
        return false;
    }

    /**
     * Determine if the stone block is carryable.
     * @return Stone block is not carryable.
     */
    @Override
    public boolean isCarryable() {
        return false;
    }
}
