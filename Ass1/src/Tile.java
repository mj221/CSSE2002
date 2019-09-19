import java.util.ArrayList;
import java.util.HashMap;

/**
 * Tiles for a map.
 * Contains Blocks
 * Maintains a mapping between exit names and other tiles.
 *
 * @author Minjae Lee
 */
public class Tile extends java.lang.Object implements java.io.Serializable{

    private java.util.List<Block> blocks = new ArrayList<>();
    private java.util.Map<java.lang.String,Tile> exits = new HashMap<>();

    /**
     * Tile constructor. Construct a new tile.
     * Each tile is constructed with no exits initially.
     * Each tile is constructed to start with 2 SoilBlock and then 1 GrassBlock(top)
     * @return nothing
     */
    public Tile(){

        exits.clear();
        blocks.add(new SoilBlock());
        blocks.add(new SoilBlock());
        blocks.add(new GrassBlock());
    }

    /**
     * Tile constructor. Construct a new tile.
     * Each tile constructed with no exits.
     * Copy the contents of startingBlocks to the blocks on tile.
     * @param startingBlocks  A list of blocks on the tile, cannot be null
     * @return nothing
     * @throws TooHighException If startingBlocks.size()>8, or
     * if startingBlocks contains an instance of GroundBlock at index 3 or higher.
     */
    public Tile(java.util.List<Block> startingBlocks)
            throws TooHighException{
        this.exits.clear();
        this.blocks = new java.util.ArrayList<Block>(startingBlocks);
        if (startingBlocks.size()>8){
            throw new TooHighException();
        }
        for (int i =0; i<startingBlocks.size()-1;i++){
            if (i>=3 && startingBlocks.get(i) instanceof GroundBlock){
                throw new TooHighException();
            }
        }
    }

    /**
     * Get exits from this tile.
     * @return exits Map of names to Tiles
     */
    public java.util.Map<java.lang.String,Tile> getExits(){
        return exits;
    }

    /**
     * Get blocks on this tile.
     * @return blocks Blocks on the Tile
     */
    public java.util.List<Block> getBlocks(){
        return blocks;
    }

    /**
     * Gets the block on the very top of the tile.
     * @return blocks The top block
     * @throws TooLowException If there are no blocks on the tile
     */
    public Block getTopBlock() throws TooLowException{
        if (getBlocks().size()==0){
            throw new TooLowException();
        }
        return getBlocks().get(getBlocks().size()-1);
    }

    /**
     * Removes the block on top of the tile.
     * @return nothing
     * @throws TooLowException If there are no blocks on the tile
     */
    public void removeTopBlock() throws TooLowException{
        if (getBlocks().size() == 0){
            throw new TooLowException();
        }else{
            blocks.remove(getBlocks().size()-1);
        }
    }

    /**
     * Add a new exit to this tile.
     * The Map returned by getExits() must now include an entry(name,target).
     * Overwrites any existing exit with the same name.
     * @param name  Name of the exit
     * @param target    Tile the exit goes to
     * @return nothing
     * @throws NoExitException If name or target is null
     */
    public void addExit(java.lang.String name,
                        Tile target)
            throws NoExitException{
        if (name == null || target == null){
            throw new NoExitException();
        }else{
            exits.put(name,target);
        }
    }

    /**
     * Remove an exit from this tile
     * The Map returned by getExits() must no longer have the key name.
     * @param name  Name of the exit to remove
     * @return nothing
     * @throws NoExitException If name is not in exits or name is null
     */
    public void removeExit(java.lang.String name)
            throws NoExitException{
        if (!getExits().containsKey(name) || name == null){
            throw new NoExitException();
        }else{
            exits.remove(name);
        }
    }

    /**
     * Attempt to dig in the current tile.
     * If the top block is diggable(.isDiggable), remove top block of tile and return it.
     * @return block The removed block
     * @throws TooLowException If there are no blocks on the tile
     * @throws InvalidBlockException If the block is not diggable
     */
    public Block dig()
            throws TooLowException,
            InvalidBlockException{
        if (getBlocks().size() == 0){
            throw new TooLowException();
        }
        if (!getTopBlock().isDiggable()){
            throw new InvalidBlockException();
        }
        return blocks.remove(getBlocks().size()-1);
    }

    /**
     * Attempt to move the current top block to another tile.
     * If the top block of the tile is moveable
     * and the height of the current tile is greater or equal to the new tile,
     * it removes the top block from the current tile and add it to the tile at the named exit
     * @param exitName  The name of the exit to move the block to
     * @return nothing
     * @throws TooHighException If the target tile is greater or equal to the current tile
     * @throws InvalidBlockException If the block is not moveable
     * @throws NoExitException If the exit is null or does not exist
     */
    public void moveBlock(java.lang.String exitName)
            throws TooHighException,
            InvalidBlockException,
            NoExitException {
        if (exitName == null || exitName.length()==0){
            throw new NoExitException();
        }
        try {
            if (!exits.get(getExits()).getTopBlock().isMoveable()){
                throw new InvalidBlockException();
            }
        } catch (TooLowException e) {
            System.out.println(e.getMessage());
        }
        if (exits.get(exitName).getBlocks().size()
                >= exits.get(getExits()).getBlocks().size()){
            throw new InvalidBlockException();
        }
        try {
            exits.get(exitName).placeBlock(exits.get(getExits()).dig());
        } catch (TooLowException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Place a block on a tile.
     * Add the block to the top of the blocks on this tile.
     * A block of GroundBlock instance cannot be placed on top (only underground [Height<3])
     * @param block  The block to place
     * @return nothing
     * @throws TooHighException If there are 8 blocks,
     * or is a ground block and the target tile has 3 or more blocks already.
     * @throws InvalidBlockException If the block is null
     */
    public void placeBlock(Block block)
            throws TooHighException,
            InvalidBlockException{
        if(block == null) {
            throw new InvalidBlockException();
        }
        if (exits.get(getExits()).getBlocks().size() >= 8) {
            throw new TooHighException();
        }
        for (int i =0; i<exits.get(getExits()).getBlocks().size()-1;i++){
            if (i>=3 && exits.get(getExits()).getBlocks().get(i) instanceof GroundBlock){
                throw new TooHighException();
            }
        }
        exits.get(getExits()).blocks.add(block);
    }
}

