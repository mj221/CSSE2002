/**
 * A wooden block or crate.
 *
 * @author Minjae Lee
 */
public class WoodBlock extends java.lang.Object implements Block {
    /**
     * WoodBlock constructor.
     */
    public WoodBlock(){}

    /**
     * Determine the colour of the wood block.
     * @return Wood block is always brown.
     */
    @Override
    public String getColour() {
        return "brown";
    }

    /**
     * Determine the block type of a wood block.
     * @return Wood block is a type of wood.
     */
    @Override
    public String getBlockType() {
        return "wood";
    }

    /**
     * Determine if wood block is diggable.
     * @return Wood block is always diggable.
     */
    @Override
    public boolean isDiggable() {
        return true;
    }

    /**
     * Determine if the soil block is moveable.
     * @return Wood block is always moveable.
     */
    @Override
    public boolean isMoveable() {
        return true;
    }

    /**
     * Determine if the soil block is carryable.
     * @return Wood block is always carryable.
     */
    @Override
    public boolean isCarryable() {
        return true;
    }
}
