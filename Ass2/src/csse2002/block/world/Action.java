package csse2002.block.world;

/**
 * Represents an Action which can be performed on the block world (also called world map).<br>
 * An action is something that a builder can do on a tile in the block world.<br>
 * The actions include, moving the builder in a direction, moving a block in a direction,
 * digging on the current tile the builder is standing on and dropping an item from a builder's inventory.<br>
 */

public class Action extends java.lang.Object{

    /*MOVE_BUILDER action which is represented by integer 0*/
    public static final int MOVE_BUILDER = 0;

    /*MOVE_BLOCK action which is represented by integer 1*/
    public static final int MOVE_BLOCK = 1;

    /*DIG action which is represented by integer 2*/
    public static final int DIG = 2;

    /*DROP action which is represented by integer 3*/
    public static final int DROP = 3;

    private int primaryAction;
    private java.lang.String secondaryAction;

    /**
     * Construct a new action.<br>
     * Create an Action that represents a manipulation of the blockworld.<br>
     * An action is represented by a primary action (one of MOVE_BUILDER, MOVE_BLOCK, DIG or DROP),
     * and a secondary action.<br>
     * @param primaryAction the action to be created.
     * @param secondaryAction  the supplementary information associated with the primary action
     */
    public Action(int primaryAction, java.lang.String secondaryAction){
        this.primaryAction = primaryAction;
        this.secondaryAction =  secondaryAction;
    }

    /**
     * Get the integer representing the Action (e.g., return 0 if Action is MOVE_BUILDER)
     * @return primaryAction the primary action
     */
    public int getPrimaryAction(){
        return primaryAction;
    }

    /**
     * Gets the supplementary information associated with the Action
     * @return the secondary action, or "" (empty string) if no secondary action exists
     */
    public java.lang.String getSecondaryAction(){
        if (secondaryAction==null){
            return "";
        }
        return secondaryAction;
    }

    /**
     * Create a single Action if possible from the given reader.
     * Read a line from the given reader and load the Action on that line.
     * Only load one Action and return the created action.
     * @param reader the reader to read the action contents form
     * @return the created action, or null if the reader is at the end of the file.
     * @throws ActionFormatException if the line has invalid contents and the action cannot be created.
     * @require reader != null
     */
    public static Action loadAction(java.io.BufferedReader reader)
            throws ActionFormatException{

        int token = 0;

        try {
            String action;

            while ((action = reader.readLine()) != null) {

                String[] actions = action.split("\\s+");

                if (actions.length>2 && actions[0]!= "DIG"){
                    throw new ActionFormatException();
                }
                else if (!actions[0].equals("MOVE_BLOCK") && !actions[0].equals("MOVE_BUILDER")
                        && !actions[0].equals("DROP") && !actions[0].equals("DIG")){
                    throw new ActionFormatException();
                }
                else if((actions[0].equals("MOVE_BlOCK") || actions[0].equals("MOVE_BUILDER")
                        || actions[0].equals("DROP"))&& actions.length==1){
                    throw new ActionFormatException();
                }
                else if (actions[0].equals("DIG") && actions.length==1){
                    throw new ActionFormatException();
                }
                else if(actions[0].equals("MOVE_BLOCK") || actions[0].equals("MOVE_BUILDER")
                        || actions[0].equals("DROP")){
                    switch (actions[0]){
                        case "MOVE_BLOCK":
                            return new Action(MOVE_BLOCK,actions[1]);
                        case "MOVE_BUILDER":
                            return new Action(MOVE_BUILDER,actions[1]);
                        case "DROP":
                            return new Action(DROP,actions[1]);
                    }
                }
                else if (actions[0].equals("DIG")){
                    return new Action(DIG,"");
                }
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
            throw new ActionFormatException();
        } finally {
            try {
                reader.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
                throw new ActionFormatException();
            }
        }
        return null;
    }

    /**
     * Read all the actions from the given reader and perform them on the given block world.
     * @param reader the reader to read actions from.
     * @throws ActionFormatException if loadAction throws an ActionFormatException.
     * @require reader != null, startingMap != null.
     */
    public static void processActions(java.io.BufferedReader reader,
                                      WorldMap startingMap)
            throws ActionFormatException{

        if (reader != null && startingMap != null){
            Action.processAction(Action.loadAction(reader), startingMap);
        }
    }

    /**
     * Perform the given action on a WorldMap, and print output to System.out.
     * After this method finishes, map should be updated.
     * @param action the action to be done on the map
     * @param map the map to perform the action on.
     * @require action != null, map != null.
     */
    public static void processAction(Action action,
                                     WorldMap map){

        String[] direction = new String[] {"north", "south", "east", "west"};

        if (action != null && map != null) {

            /*For dig action*/
            if (action.getPrimaryAction() == DIG) {
                try {
                    map.getBuilder().digOnCurrentTile();
                } catch (TooLowException e) {
                    System.out.println("Too Low");
                    assert(false);
                } catch (InvalidBlockException e) {
                    System.out.println("Cannot use that block");
                    assert(false);
                }
                System.out.println("Top block on current tile removed");
            }
            /*For drop action*/
            else if (action.getPrimaryAction() == DROP) {
                try{
                    map.getBuilder().dropFromInventory(Integer.parseInt(action.getSecondaryAction()));
                } catch(NumberFormatException e){
                    System.out.println("Error: Invalid Action");
                    assert(false);
                } catch (InvalidBlockException e) {
                    System.out.println("Cannot use that block");
                    assert(false);
                } catch (TooHighException e) {
                    System.out.println("Too high");
                    assert(false);
                }
            }
            /*For move_block action*/
            else if (action.getPrimaryAction()==MOVE_BLOCK){
                try {
                    map.getBuilder().getCurrentTile().moveBlock(action.getSecondaryAction());
                } catch (TooHighException e) {
                    System.out.println("Too high");
                    assert(false);
                } catch (InvalidBlockException e) {
                    System.out.println("Cannot use that block");
                    assert(false);
                } catch (NoExitException e) {
                    System.out.println("No exit this way");
                    assert(false);
                }
                System.out.println("Moved block {"+action.getSecondaryAction()+"}");
            }
            /*For move_builder action*/
            else if (action.getPrimaryAction()==MOVE_BUILDER){
                try {
                    map.getBuilder().moveTo(map.getBuilder().getCurrentTile()
                            .getExits().get(action.getSecondaryAction()));
                } catch (NoExitException e) {
                    System.out.println("No exit this way");
                    assert(false);
                }
                System.out.println("Moved builder {"+action.getSecondaryAction()+"}");
            }
            else if (action.getPrimaryAction() < 0 || action.getPrimaryAction()>3){
                System.out.println("Error: Invalid action");
            }
            if (action.getPrimaryAction()==MOVE_BLOCK || action.getPrimaryAction()==MOVE_BUILDER){
                for (String p : direction){
                    if (!action.getSecondaryAction().contains(p)){
                        System.out.println("Error: Invalid action");
                    }
                }
            }
        }
    }
}
