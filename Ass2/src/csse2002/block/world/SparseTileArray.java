package csse2002.block.world;

import java.util.*;

/**
 * A sparse representation of tiles in an Array.
 * Contains Tiles stored with an associated Position (x, y) in a map.
 */

public class SparseTileArray extends java.lang.Object {

    /*construct a Map to allow looking up tiles by position*/
    private Map<Position, Tile> SparseTileMap;

    /**
     * Constructor for a SparseTileArray. Initializes an empty SparseTileArray, such that getTile(new Position(x, y))
     * returns null for any x and y and getTiles() returns an empty list.
     */
    public SparseTileArray(){
        SparseTileMap= new TreeMap<Position, Tile>();
    }

    /**
     * Get the tile at position at (x, y), given by position.getX() and position.getY().
     * Return null if there is no tile at (x, y).
     * @param position the tile position.
     * @return the tile at (x, y) or null if no such tile exists.
     * @require position != null.
     */
    public csse2002.block.world.Tile getTile(Position position){
        for (Position p: SparseTileMap.keySet()){
            if (p.equals(position)){
                return SparseTileMap.get(p);
            }
        }
        return null;
    }

    /**
     * Get a set of ordered tiles from SparseTileArray in breadth-first-search order.
     * The startingTile (passed to addLinkTiles) should be the first tile in the list.
     * The following tiles is at the "north", "east", "south" and "west" exits from the starting tile,
     * if they exist.
     * @return a list of tiles in breadth-first-search order.
     */
    public java.util.List<csse2002.block.world.Tile> getTiles(){
        return new LinkedList<Tile>(SparseTileMap.values());
    }

    /**
     * Add a set of tiles to the sparse tilemap.
     * @param startingTile the starting point in adding the linked tiles. All added tiles must have a path
     *                     (via multiple exits) to this tile.
     * @param startingX the x coordinate of startingTile in the array.
     * @param startingY the y coordinate of startingTile in the array.
     * @throws WorldMapInconsistentException if the tiles in the set are not geometrically consistent.
     * @require startingTile != null
     * @ensure tiles accessed through getTile() are geometrically consistent
     */
    public void addLinkedTiles(csse2002.block.world.Tile startingTile,
                               int startingX,
                               int startingY)
            throws WorldMapInconsistentException{
        SparseTileMap = new TreeMap<>();
        SparseTileMap.put(new Position(startingX,startingY),startingTile);

        startingTile.getExits().get("north").getExits().get("east").getExits().get("");

        Queue<Tile> nodesToExit = new LinkedList<>();
        Set<Tile> alreadyVisitedExit = new HashSet<>();

        nodesToExit.add(startingTile);

        while (nodesToExit.size() !=0){
            Tile exit = nodesToExit.remove();

            if(!alreadyVisitedExit.contains(exit)){
                alreadyVisitedExit.add(exit);

            }
        }
    }

}
