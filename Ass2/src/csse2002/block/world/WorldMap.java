package csse2002.block.world;

import java.io.*;

/**
 * A class to store a world map.
 */

public class WorldMap extends java.lang.Object{

    /*Store builder associated with this block world*/
    private csse2002.block.world.Builder builder;

    /*Store starting position*/
    private Position startPosition;

    /**
     * Constructs a new block world map from a startingTile, position and builder,
     * such that getBuilder() == builder, getStartPosition() == startPosition,
     * and getTiles() returns a list of tiles that are linked to startingTile.
     * @param startingTile the tile which the builder starts on.
     * @param startPosition the position of the starting tile.
     * @param builder the builder who will traverse the block world.
     * @throws WorldMapInconsistentException if there are inconsistencies in the positions of tiles (such as two tiles at a single position).
     * @require startingTile != null, startPosition != null, builder != null, builder.getCurrentTile() == startingTile.
     */
    public WorldMap(csse2002.block.world.Tile startingTile,
                    Position startPosition,
                    csse2002.block.world.Builder builder)
        throws WorldMapInconsistentException{

        if (startingTile != null && startPosition != null && builder.getCurrentTile() == startingTile){
            WorldMap start = new WorldMap(startingTile,startPosition,builder);
            start.builder = builder;
            start.startPosition = startPosition;
        }
        else{
            throw new WorldMapInconsistentException();
        }

    }

    /**
     * Construct a block world map from the given filename, according to the provided block world format.
     * @param filename the name to load the file from.
     * @throws WorldMapFormatException if the file is incorrectly formatted.
     * @throws WorldMapInconsistentException if the file is correctly formatted, but has inconsistencies (such as overlapping tiles).
     * @throws java.io.FileNotFoundException if the file does not exist.
     * @require filename != null.
     * @ensure the loaded map is geometrically consistent.
     */
    public WorldMap(String filename)
            throws WorldMapFormatException,
            WorldMapInconsistentException,
            FileNotFoundException{

        if (filename != null){
            BufferedReader f= new BufferedReader(new FileReader(new File(filename)));
            BufferedReader NB= new BufferedReader(new FileReader(new File(filename)));
            BufferedReader NP= new BufferedReader(new FileReader(new File(filename)));
            BufferedReader NT= new BufferedReader(new FileReader(new File(filename)));
            BufferedReader NI= new BufferedReader(new FileReader(new File(filename)));

            /*String map = "";
            while ((map=f.readLine())!=null){
                
            }*/

            String NewBuilder = "";
            for (int i=0; i <2;i++){
                try {
                    NB.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                NewBuilder=NB.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String NewPositionX = "";
            String NewPositionY = "";
            try {
                NewPositionX=NP.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                NewPositionY=NP.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String NewTile= "";
            for (int i=0; i <6;i++){
                try {
                    NT.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                NewTile=NT.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String NewInv="";
            for (int i=0;i<3;i++){
                try {
                    NI.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                NewInv=NI.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            throw new FileNotFoundException();
        }
    }

    /**
     * Gets the builder associated with this block world.
     * @return  the builder object.
     */
    public csse2002.block.world.Builder getBuilder(){
        return builder;
    }

    /**
     * Gets the starting position..
     * @return  the starting position.
     */
    public Position getStartPosition(){
        return startPosition;
    }

    /**
     * Get a tile by position.
     * @param position get the Tile at this position.
     * @return  the tile at that position
     * @require position != null.
     */
    public csse2002.block.world.Tile getTile(Position position){
        if (position != null){
            return new csse2002.block.world.SparseTileArray().getTile(position);
        }
        return null;
    }

    /**
     *Get a list of tiles in a breadth-first-search order.
     * @return  a list of ordered tiles.
     */
    public java.util.List<csse2002.block.world.Tile> getTiles(){
        return new csse2002.block.world.SparseTileArray().getTiles();
    }

    /**
     * Saves the given WorldMap to a file specified by the filename accordingly to the map format.
     * @param filename the filename to be written to.
     * @throws java.io.IOException if the file cannot be opened or written to.
     * @require filename != null.
     */
    public void saveMap(java.lang.String filename)
            throws java.io.IOException{
        if (filename != null){
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        }
    }
}
