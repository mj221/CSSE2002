/**
 * A grass block.
 *
 * @author Minjae Lee
 */
public class GrassBlock extends GroundBlock {
    /**
     * GrassBlock constructor.
     */
    public GrassBlock(){}

    /**
     * Determine the colour of grass block.
     * @return Always return colour 'green'.
     */
    @Override
    public String getColour() {
        return "green";
    }

    /**
     * Determine the type of grass block.
     * @return Always return block type 'grass'.
     */
    @Override
    public String getBlockType() {
        return "grass";
    }

    /**
     * Determine if grass blocks are carryable.
     * @return Always return false.
     */
    @Override
    public boolean isCarryable() {
        return false;
    }
}
