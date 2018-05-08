package sk.sim.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import sk.sim.gui.model.RestaurantFx;
import sk.sim.gui.model.Simulation;
import sk.sim.utill.Logger;

public class MainWindowController
{
    @FXML
    private TextField guestsNumber;

    @FXML
    private TextField waitersNumber;

    @FXML
    private TextField mealsNumber;

    @FXML
    private TextField drinksNumber;

    @FXML
    private Button startButton;

    @FXML
    private TextArea logTextArea;


    private Simulation simulation;

    @FXML
    public void initialize()
    {
        simulation = new Simulation();

        Logger.loggerr = logTextArea;

        bindTextFields();
    }

    @FXML
    public void startSimulationOnAction()
    {
        simulation.start();
        startButton.setDisable(true);
    }

    private void bindTextFields()
    {
        RestaurantFx restaurantFx = simulation.getRestaurantFx();


        NumberStringConverter converter = new NumberStringConverter();
        guestsNumber.textProperty().bindBidirectional(restaurantFx.guestNumberIntegerProperty(), converter);
        waitersNumber.textProperty().bindBidirectional(restaurantFx.waitersNumberIntegerProperty(), converter);
        mealsNumber.textProperty().bindBidirectional(restaurantFx.mealsNumberIntegerProperty(), converter);
        drinksNumber.textProperty().bindBidirectional(restaurantFx.drinksNumberIntegerProperty(), converter);
    }
}
