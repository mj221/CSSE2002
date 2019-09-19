/**
 * An abstract GroundBlock that enforces not moveable and diggable.
 *
 * @author Minjae Lee
 */
public abstract class GroundBlock extends java.lang.Object implements Block{
    /**
     * GroundBlock constructor.
     */
    public GroundBlock(){}

    /**
     * Determine if the ground block is moveable.
     * @return Always return 'false'.
     */
    @Override
    public final boolean isMoveable() {
        return false;
    }

    /**
     * Determine if the ground block is diggable.
     * @return Always return 'true'.
     */
    @Override
    public final boolean isDiggable() {
        return true;
    }

}
