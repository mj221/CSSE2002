package game;

import csse2002.block.world.TooLowException;
import csse2002.block.world.WorldMap;
import csse2002.block.world.WorldMapFormatException;
import csse2002.block.world.WorldMapInconsistentException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Controller class for the GUI
 * <p>
 * Used to save and load worlds.
 */
public class SaveLoadHandle {
    /*The view of application*/
    private View view;

    /*A boolean to check fail on exceptions*/
    private boolean fail = false;

    /**
     * Constructor
     * When a Controller is created, add the EventHandlers to the view
     * @param view
     *          The view for this application
     */
    public SaveLoadHandle(View view){
        this.view=view;
        view.addSaveLoadHandler(new SaveLoadHandler());
    }

    /**
     * EventHandler for the menu items, which reads the inputs to load or save.
     * @author Minjae Lee
     */
    private class SaveLoadHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            /*get the item which was just pressed*/
            MenuItem clickeditem = (MenuItem) event.getSource();

            if (clickeditem.getText() == "Load World Map"){
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load World Map");
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    view.clearCanvas();
                    view.clearInvTxtLabe();
                    try {
                        WorldMap NewWorld = new WorldMap(selectedFile.toString());
                        view.displayWorld(NewWorld);
                        fail = false;
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Map Could Not Be Loaded! \nerror:"+e1);
                        fail = true;
                    } catch (WorldMapFormatException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Map Could Not Be Loaded! \nerror:"+e1);
                        fail = true;
                    } catch (WorldMapInconsistentException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Map Could Not Be Loaded! \nerror:"+e1);
                        fail = true;
                    } catch (TooLowException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Map Could Not Be Loaded! \nerror:"+e);
                        fail  = true;
                    }
                    if (fail != true){
                        view.enableAllButton();
                        JOptionPane.showMessageDialog(null, "Map Successfully Loaded.");

                    }
                }

            }else if(clickeditem.getText() == "Save World Map"){
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save World Map");
                File selectedFile = fileChooser.showSaveDialog(null);
                if (selectedFile != null){
                    try {
                        WorldMap NewWorld = new WorldMap(selectedFile.toString());
                        view.saveWorld(NewWorld,selectedFile.toString());
                    } catch (WorldMapFormatException e) {
                        e.printStackTrace();
                    } catch (WorldMapInconsistentException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }
}