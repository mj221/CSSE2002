package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * The Controller class for the GUI
 * <p>
 * Used to control the View depending on user input
 */
public class Controller {

    /* The view of our application */
    private View view;

    /**
     * Constructor
     * When a Controller is created, add the EventHandlers to the view.
     * Handles all actions.
     * @param view
     *          The view for this application
     */
    public Controller(View view) {
        this.view = view;
        view.addActionHandler(new ActionHandler());
    }

    /**
     * EventHandler for the buttons, which performs different functions
     * accordingly to user input.
     * @author Minjae Lee
     */
    private class ActionHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            /* Get the button which was just pressed */
            Button pressedButton = (Button) event.getSource();

            if (pressedButton.getText()=="Drop"){
                view.showDropInv();
            }

        }

    }

}
