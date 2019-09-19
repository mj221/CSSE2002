package csse2002.block.world;

/**
 * Represents the position of a Tile in the SparseTileArray.
 */

public class Position extends java.lang.Object implements java.lang.Comparable<Position>{

    /*store x coordinate*/
    private int x;

    /*store y coordinate*/
    private int y;

    /**
     * Construct a position for (x, y).
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x coordinate.
     * @return x coordinate.
     */
    public int getX(){
        return x;
    }

    /**
     * Get the y coordinate.
     * @return y coordinate.
     */
    public int getY(){
        return y;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj the object to compare to.
     * @return true if obj is an instance of Position and if obj.x == x and obj.y == y.
     */
    public boolean equals(java.lang.Object obj){
        if (obj instanceof Position && ((Position) obj).x==x && ((Position) obj).y==y){
            return true;
        }
        return false;
    }

    /**
     * Compute a hashCode that meets the contract of Object.hashCode
     * @override hashCode in class java.lang.Object.
     * @return a suitable hashcode for the Position.
     */
    public int hashCode(){
        return 1;
    }

    /**
     * Compare this position to another position.
     * @param other the other Position to compare to.
     * @return -1, 0, or 1 depending on the conditions.
     */
    public int compareTo(Position other) {
        if (getX() < other.getX()){
            return -1;
        }
        else if (getX()==other.getX() && getY()<other.getY()){
            return -1;
        }
        else if  (getX()==other.getX() && getY()==other.getY()){
            return 0;
        }
        else if(getX() > other.getX()){
            return 1;
        }
        else if(getX() == other.getX() && getY() > other.getY() ){
            return 1;
        }
        return 0;
    }

    /**
     * Convert this position to a string "(<x>, <y>)".
     * @return a string representation of the position "(<x>, <y>)".
     */
    public java.lang.String toString(){
        return "("+getX()+", "+getY()+")";
    }
}
