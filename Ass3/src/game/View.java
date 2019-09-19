package game;

import csse2002.block.world.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.io.IOException;

/**
 * The View class for the Block World Game.
 * <p>
 * This class creates the GUI view and has methods which can update to the created view
 *
 */
public class View {
    /* The root node of the scene graph, to add all the GUI elements to*/
    private HBox rootBox;

    /* Canvas context, this context allows us to draw stuff on the canvas */
    private GraphicsContext context;

    /* Buttons to cause actions */
    private Button[] buttons;

    /* Stores all in game labels */
    private Label[] labels;

    /* Menu items in menu bar */
    private MenuItem[] menuitems;

    /* Textfield to enter drop index */
    private TextField dropIndex;

    /* Stores canvas sizes */
    public double canvasX;
    public double canvasY;

    /* A new world */
    WorldMap NewWorld;

    /**
     * Constructor
     * <p>
     * When a view is created, start building the initial scene graph,
     * by adding all the necessary components.
     */
    public View() {

        rootBox = new HBox();
        addComponents();

    }

    /**
     * Adds the given handler to the given ith button
     * @param handler
     *          the handler to add to the button
     */
    public void addActionHandler(EventHandler<ActionEvent> handler) {
        /* Adds the handler to the setOnAction meaning when button is pressed
         * the handle() method inside this handler will be called
         */
        for(Button a : buttons) {
            a.setOnAction(handler);
        }
    }

    /**
     * Adds the given handler to the given ith button
     * @param handler
     *          the handler to add to the button
     */
    public void addSaveLoadHandler(EventHandler<ActionEvent> handler) {
        /* Adds the handler to the setOnAction meaning when button is pressed
         * the handle() method inside this handler will be called
         */
        for(MenuItem b : menuitems) {
            b.setOnAction(handler);
        }
    }

    /**
     * Get the Scene of the GUI with the scene graph
     * @return
     *          the current scene
     */
    public Scene getScene() {
        return new Scene(rootBox);
    }

    /**
     * Draws the blocks and builder at given x and y coordinates
     * View atm can only draw
     * - Grass
     * - Soil
     * - Stone
     * - Wood
     * - Builder
     * @param x
     *          the X coordinate of the shape
     * @param y
     *          the Y coordinate of the shape
     */
    public void drawGrassBlock(int x, int y){
        context.setFill(Color.GREEN);
        context.fillRect(x,y,50,50);

    }
    public void drawSoilBlock(int x, int y){
        context.setFill(Color.BLACK);
        context.fillRect(x,y,50,50);
    }
    public void drawStoneBlock(int x, int y){
        context.setFill(Color.GRAY);
        context.fillRect(x,y,50,50);
    }
    public void drawWoodBlock(int x, int y){
        context.setFill(Color.BROWN);
        context.fillRect(x,y,50,50);
    }
    public void drawBuilder(int x, int y){
        context.setFill(Color.YELLOW);
        context.fillOval(x, y, 15, 15);
    }

    /**
     * Adds all the GUI elements to the root layout
     * <p>
     * These is where the scene graph is created
     */
    private void addComponents() {
        Menu file = new Menu("File");
        MenuItem loadWorld = new MenuItem("Load World Map");
        MenuItem saveWorld = new MenuItem("Save World Map");
        menuitems = new MenuItem[2];
        menuitems[0] = loadWorld;
        menuitems[1] = saveWorld;

        //loadWorld.setOnAction(new LoadWorldMap());
        //saveWorld.setOnAction(new SaveWorldMap());
        file.getItems().addAll(menuitems[0],menuitems[1]);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(file);



        VBox leftBox = new VBox(menuBar);

        /* Add padding, colour to the left side */
        leftBox.setPadding(new Insets(10, 10, 10, 10));
        leftBox.setStyle("-fx-background-color: white");
        addLeftSideComponents(leftBox);

        /* Another layout node for the left side of the GUI */
        VBox rightBox = new VBox();

        /* add colour and padding to the right layout */
        rightBox.setSpacing(10);
        rightBox.setPadding(new Insets(20, 20, 20, 20));
        rightBox.setStyle("-fx-background-color: white");
        addRightSideComponents(rightBox);
        BorderPane.setAlignment(rightBox, Pos.CENTER);

        /* add both layouts to the root HBox layout*/
        rootBox.getChildren().addAll(leftBox, rightBox);

    }

    /**
     * Add all the Gui elements to the left container,
     * such as the canvas and the text fields
     *
     * @param box
     *          the container to add the elements to
     */
    private void addLeftSideComponents(VBox box) {

        /* add the canvas inside a HBox */
        HBox canvasContainer = new HBox();
        Canvas canvas = new Canvas(450, 450);
        canvasX=canvas.getHeight();
        canvasY=canvas.getWidth();
        context = canvas.getGraphicsContext2D();
        canvasContainer.getChildren().add(canvas);
        canvasContainer.setStyle("-fx-border-color: brown");
        /* the hBox (canvasContainer) is used so that border can be added around the canvas */


        /* Create another HBox and add textInputs and Labels inside it */
        HBox inputBox = new HBox();
        inputBox.setPadding(new Insets(10, 10, 10, 10));
        inputBox.setSpacing(15);
        /* Add labels and inputs to the HBox */
        labels = new Label[2];
        Label InvLabel = new Label("Builder Inventory:");
        Label InvLabe2 = new Label("");
        labels[0]= InvLabel;
        labels[1]= InvLabe2;

        /* Make everything inside the HBox Center aligned */
        inputBox.setAlignment(Pos.CENTER);
        inputBox.getChildren().addAll(InvLabel, InvLabe2);

        /* Add another textField to the VBox to display previous drawn command
         * Could use a Label instead of a TextField, but I chose not to :P
         */

        /* add everything to the left VBox (which is passed as argument) */
        box.getChildren().addAll(canvasContainer, inputBox);

    }


    /**
     * Add all the GUI elements to the right container,
     * which consists of all the buttons.
     *
     *  Initially disable all buttons.
     *
     * @param box
     *          the container to add the elements to
     */
    private void addRightSideComponents(VBox box) {

        /* add buttons to the VBox*/
        buttons = new Button[6];
        String[] buttonLabels = {"NORTH", "SOUTH", "EAST", "WEST"};
        for (int i = 0; i < buttons.length-2; i++) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].setPrefSize(100, 30);
            box.getChildren().add(buttons[i]);
        }
        ObservableList<String> actions = FXCollections.observableArrayList("MOVE_BUILDER","MOVE_BLOCK");
        ComboBox actionlist = new ComboBox(actions);
        actionlist.setPromptText("CHOOSE_ACTION");
        box.getChildren().add(actionlist);

        dropIndex = new TextField();
        dropIndex.setPromptText("Drop Index");
        dropIndex.setEditable(true);

        Button dig = new Button("Dig");
        Button drop = new Button("Drop");
        dig.setPrefSize(70, 20);
        drop.setPrefSize(70, 20);
        buttons[4]= dig;
        buttons[5] = drop;

        for(int i = 0; i<buttons.length;i++){
            buttons[i].setDisable(true);
        }

        box.getChildren().addAll(dig, drop, dropIndex);
    }

    /**
     * Display the loaded world onto the canvas.
     *
     * @param world
     *          the world we loaded.
     */
    public void displayWorld(WorldMap world) throws TooLowException {
        this.NewWorld = world;
        String blockname[] = new String[NewWorld.getTiles().size()];
        blockname[0] = null;
        blockname[0] = NewWorld.getTile(NewWorld.getStartPosition()).getTopBlock().getBlockType();

        System.out.println(getGridPos(NewWorld.getStartPosition().getX()));
        System.out.println("Y:"+getGridPos(NewWorld.getStartPosition().getY()*50));

        System.out.println(NewWorld.getTiles().get(0).getExits().toString());

        switch (blockname[0]){
            case "soil": drawSoilBlock(getGridPos(NewWorld.getStartPosition().getX()),
                    getGridPos(NewWorld.getStartPosition().getY()));
            break;
            case "grass": drawGrassBlock(getGridPos(NewWorld.getStartPosition().getX()),
                    getGridPos(NewWorld.getStartPosition().getY()));
            break;
            case "stone": drawStoneBlock(getGridPos(NewWorld.getStartPosition().getX()),
                    getGridPos(NewWorld.getStartPosition().getY()));
            break;
            case "wood": drawWoodBlock(getGridPos(NewWorld.getStartPosition().getX()),
                    getGridPos(NewWorld.getStartPosition().getY()));
            break;
        }

        for (int i=0;i<=NewWorld.getTiles().get(0).getExits().size();i++){
            if (NewWorld.getTile(NewWorld.getStartPosition()).getExits().get("east")==
                    NewWorld.getTiles().get(i)){
                System.out.println(NewWorld.getTiles().get(i).getTopBlock().getBlockType());
                blockname[1] = NewWorld.getTiles().get(i).getTopBlock().getBlockType();
                switch (blockname[1]){
                    case "soil": drawSoilBlock(getGridPos(NewWorld.getStartPosition().getX()+1),
                            getGridPos(NewWorld.getStartPosition().getY()));
                        break;
                    case "grass": drawGrassBlock(getGridPos(NewWorld.getStartPosition().getX()+1),
                            getGridPos(NewWorld.getStartPosition().getY()));
                        break;
                    case "stone": drawStoneBlock(getGridPos(NewWorld.getStartPosition().getX()+1),
                            getGridPos(NewWorld.getStartPosition().getY()));
                        break;
                    case "wood": drawWoodBlock(getGridPos(NewWorld.getStartPosition().getX()+1),
                            getGridPos(NewWorld.getStartPosition().getY()));
                        break;
                }
            }
            if (NewWorld.getTile(NewWorld.getStartPosition()).getExits().get("west")==
                    NewWorld.getTiles().get(i)){
                System.out.println(NewWorld.getTiles().get(i).getTopBlock().getBlockType());
                blockname[1] = NewWorld.getTiles().get(i).getTopBlock().getBlockType();
                switch (blockname[1]){
                    case "soil": drawSoilBlock(getGridPos(NewWorld.getStartPosition().getX()-1),
                            getGridPos(NewWorld.getStartPosition().getY()));
                        break;
                    case "grass": drawGrassBlock(getGridPos(NewWorld.getStartPosition().getX()-1),
                            getGridPos(NewWorld.getStartPosition().getY()));
                        break;
                    case "stone": drawStoneBlock(getGridPos(NewWorld.getStartPosition().getX()-1),
                            getGridPos(NewWorld.getStartPosition().getY()));
                        break;
                    case "wood": drawWoodBlock(getGridPos(NewWorld.getStartPosition().getX()-1),
                            getGridPos(NewWorld.getStartPosition().getY()));
                        break;
                }
            }
            if (NewWorld.getTile(NewWorld.getStartPosition()).getExits().get("north")==
                    NewWorld.getTiles().get(i)){
                System.out.println(NewWorld.getTiles().get(i).getTopBlock().getBlockType());
                blockname[1] = NewWorld.getTiles().get(i).getTopBlock().getBlockType();
                switch (blockname[1]){
                    case "soil": drawSoilBlock(getGridPos(NewWorld.getStartPosition().getX()),
                            getGridPos(NewWorld.getStartPosition().getY()-1));
                        break;
                    case "grass": drawGrassBlock(getGridPos(NewWorld.getStartPosition().getX()),
                            getGridPos(NewWorld.getStartPosition().getY()-1));
                        break;
                    case "stone": drawStoneBlock(getGridPos(NewWorld.getStartPosition().getX()),
                            getGridPos(NewWorld.getStartPosition().getY()-1));
                        break;
                    case "wood": drawWoodBlock(getGridPos(NewWorld.getStartPosition().getX()),
                            getGridPos(NewWorld.getStartPosition().getY()-1));
                        break;
                }
            }
            if (NewWorld.getTile(NewWorld.getStartPosition()).getExits().get("south")==
                    NewWorld.getTiles().get(i)){
                System.out.println(NewWorld.getTiles().get(i).getTopBlock().getBlockType());
                blockname[1] = NewWorld.getTiles().get(i).getTopBlock().getBlockType();
                switch (blockname[1]){
                    case "soil": drawSoilBlock(getGridPos(NewWorld.getStartPosition().getX()),
                            getGridPos(NewWorld.getStartPosition().getY()+1));
                        break;
                    case "grass": drawGrassBlock(getGridPos(NewWorld.getStartPosition().getX()),
                            getGridPos(NewWorld.getStartPosition().getY()+1));
                        break;
                    case "stone": drawStoneBlock(getGridPos(NewWorld.getStartPosition().getX()),
                            getGridPos(NewWorld.getStartPosition().getY()+1));
                        break;
                    case "wood": drawWoodBlock(getGridPos(NewWorld.getStartPosition().getX()),
                            getGridPos(NewWorld.getStartPosition().getY()+1));
                        break;
                }
            }
        }


        drawBuilder((int)canvasX/2,(int)canvasY/2);

        showInv();

    }

    /**
     * Saves the world as it is.
     *
     * @param world
     *          the container to add the elements to
     * @param name
     *          name of world.
     */
    public void saveWorld(WorldMap world, String name){
        this.NewWorld = world;
        try {
            NewWorld.saveMap(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert necessary grid numbers
     *
     * @param num
     *          the number to convert.
     */
    public int getGridPos(int num){
        return num *50;
    }

    /**
     * Obtain the drop index from text field
     *
     */
    private int getDropIndex(){
        int index;
        try {
            index = Integer.parseInt(dropIndex.getText());
        }catch (NumberFormatException e){
            index = 0;
        }
        return index;}

    /**
     * Update inventory label after dropping item
     */
    public void showDropInv(){
        try {
            NewWorld.getBuilder().dropFromInventory(getDropIndex());
        } catch (InvalidBlockException e) {
            e.printStackTrace();
        } catch (TooHighException e) {
            e.printStackTrace();
        }
        for (int i=0; i<NewWorld.getBuilder().getInventory().size();i++){
            labels[1].setText("|"+NewWorld.getBuilder().getInventory().get(i).getBlockType()+"|");
        }
    }

    /**
     * Updates inventory
     */
    public void showInv(){

        for (int i=0; i<NewWorld.getBuilder().getInventory().size();i++){
            labels[1].setText(labels[1].getText()+"|"+NewWorld.getBuilder().getInventory().get(i).getBlockType()+"|");
        }
    }

    /**
     * clear the whole canvas
     * <p>
     * remove w/e was drawn on it
     */
    public void clearCanvas(){
        context.clearRect(0, 0, context.getCanvas().getWidth(),
                context.getCanvas().getHeight());
    }
    /**
     * clear the inventory label
     */
    public void clearInvTxtLabe(){
        labels[1].setText("");
    }

    /**
     * enable all buttons
     */
    public void enableAllButton(){
        for(int i = 0; i<buttons.length;i++){
            buttons[i].setDisable(false);
        }
    }
}