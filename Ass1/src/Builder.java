import java.util.ArrayList;

/**
 * Initiate name of player who modifies the map
 * Maintains a position in the map (by current tile)
 * Manages an inventory of Blocks
 *
 * @author Minjae Lee
 */
public class Builder extends java.lang.Object{

    private java.lang.String name;
    private Tile CurrentTile;
    private java.util.List<Block> Inventory;

    /**
     * Builder constructor. Create a builder.
     * Set the name of builder and the current tile to startingTile.
     * @param name  Builder's name
     * @param startingTile  The tile the builder starts in
     * @return nothing
     */
    public Builder(java.lang.String name, Tile startingTile){
        this.name = name;
        this.CurrentTile = startingTile;
        this.Inventory = new java.util.ArrayList<Block>();
    }

    /**
     * Builder constructor. Create a builder.
     * Set the name of builder and the current tile to startingTile.
     * Copy the starting inventory into the builder's inventory.
     * @param name  Builder's name
     * @param startingTile  The tile the builder starts in
     * @param startingInventory The inventory the builder starts in
     * @return nothing
     * @throws InvalidBlockException if for any Block (block)
     * in startingInventory is not carryable.
     */
    public Builder(java.lang.String name, Tile startingTile,
                   java.util.List<Block> startingInventory)
            throws InvalidBlockException{

        this.name = name;
        this.CurrentTile = startingTile;
        for (int i =0; i<startingInventory.size()-1;i++){
            if (startingInventory.get(i).isCarryable() ==false){
                throw new InvalidBlockException();
            }
        }
        this.Inventory = new java.util.ArrayList<Block>(startingInventory);
    }

    /**
     * Get the builder's name
     * @return name Returns the builder's name
     */
    public java.lang.String getName(){
        return name;
    }

    /**
     * Get the current tile that the builder is on
     * @return CurrentTile return the current tile
     */
    public Tile getCurrentTile(){
        return CurrentTile;
    }

    /**
     * Get the Builder's inventory
     * @return Inventory return the blocks in the inventory
     */
    public java.util.List<Block> getInventory(){
        return Inventory;
    }

    /**
     * Drops a block from the inventory on the top of the current tile.
     * The block at inventoryIndex is removed from the inventory and added to the current tile.
     * Blocks can only be dropped on tiles with <8 blocks
     * or tiles with <3 blocks if a GroundBlock.
     * @param inventoryIndex  The index in the inventory to place
     * @return nothing
     * @throws InvalidBlockException if the inventoryIndex is out of the inventory range
     * @throws TooHighException if there are 8 blocks already
     * or if 3 or more blocks on the current tile with the instance of GroundBlock.
     */
    public void dropFromInventory(int inventoryIndex)
            throws InvalidBlockException,
            TooHighException{
        if (inventoryIndex < 0 || inventoryIndex >= getInventory().size()){
            throw new InvalidBlockException();
        }
        if (getCurrentTile().getBlocks().size() >= 8){
            throw new TooHighException();
        }
        for (int i =0; i<getCurrentTile().getBlocks().size()-1;i++){
            if (getCurrentTile().getBlocks().size() >=3
                    && getInventory().get(i) instanceof GroundBlock){
                throw new TooHighException();
            }
        }
        CurrentTile.placeBlock(getInventory().get(inventoryIndex));
    }

    /**
     * Attempt to dig in the current tile and then add tile to the inventory.
     * If the top block of the tile is diggable, remove the top block and destroy it
     * or add it to the end of the inventory (if the block is carryable).
     * @return nothing
     * @throws TooLowException if there are no blocks on the current tile.
     * @throws InvalidBlockException if the top block is not diggable.
     */
    public void digOnCurrentTile()
            throws TooLowException,
            InvalidBlockException{
        if (getCurrentTile().getBlocks().size()==0){
            throw new TooLowException();
        }
        if (!getCurrentTile().getTopBlock().isDiggable()){
            throw new InvalidBlockException();
        }
        if (!getCurrentTile().getTopBlock().isCarryable()){
            CurrentTile.dig();
        }else{
            Inventory.add(CurrentTile.dig());
        }
    }

    /**
     * Checks if the Builder can enter a tile from the current tile.
     * @param newTile   The tile to test if the Builder can enter
     * @return True If the tiles are connected via an exit and the height of the new tile
     * is same or different by 1.
     * @return False If newTile is null or above conditions are not satisfied
     */
    public boolean canEnter(Tile newTile){
        if (newTile == null) {
            return false;
        }
        if (getCurrentTile().getExits().containsValue(newTile)
                && java.lang.Math.abs(getCurrentTile().getBlocks().size()
                - newTile.getBlocks().size()) <= 1){
            return true;
        }
        return false;
    }

    /**
     * Move the builder to a new tile.
     * If canEnter(newTile) == true then the builder's current tile is the new tile.
     * @param newTile  The tile to move to
     * @return nothing
     * @throws NoExitException if canEnter(newTile) == false
     */
    public void moveTo(Tile newTile)
            throws NoExitException{
        if (canEnter(newTile) == false){
            throw new NoExitException();
        }else{
            this.CurrentTile = newTile;
        }
    }
}
