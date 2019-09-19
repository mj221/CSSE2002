/**
 * A soil block.
 *
 * @author Minjae Lee
 */
public class SoilBlock extends GroundBlock {
    /**
     * SoilBlock constructor.
     */
    public SoilBlock(){}

    /**
     * Determine the colour of soil block.
     * @return Always return 'black'.
     */
    @Override
    public String getColour() {
        return "black";
    }

    /**
     * Determine the type of a soil block.
     * @return Always return 'soil'.
     */
    @Override
    public String getBlockType() {
        return "soil";
    }

    /**
     * Determine if the soil block is carryable.
     * @return Always return 'true'.
     */
    @Override
    public boolean isCarryable() {
        return true;
    }
}
