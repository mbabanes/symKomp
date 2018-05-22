package sk.sim.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;
import sk.sim.gui.model.GuestsStatistics;
import sk.sim.gui.model.RestaurantFx;
import sk.sim.gui.model.Simulation;
import sk.sim.gui.visualisation.Visualisation;
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
    private TextField cookNumber;

    @FXML
    private Button startButton;

    @FXML
    private TextArea logTextArea;

    @FXML
    private TextArea statsTextArea;

    @FXML
    private Pane canvas;

    @FXML
    private Label endLabel;

    @FXML
    private Label queueLabel;

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

        putMessage("Statystyki kelnerów:\n", simulation.waiterStatistics());

        putMessage("\nStatystyki kucharzy:\n", simulation.cookersStatistics());

        putMessage("\nStatystyki zamówień:\n", simulation.mealsAndDrinksStatistics());


        putDescriptiveStats();

//        runVisualisation();
    }

    private void putMessage(String narrow, String message)
    {
        statsTextArea.appendText(narrow);
        statsTextArea.appendText(message);
    }

    private void putDescriptiveStats()
    {
        statsTextArea.appendText("\nStatystyki opisowe:\n");
        String message = new GuestsStatistics().getMessage();
        statsTextArea.appendText(message);
    }

    private void runVisualisation()
    {
        Visualisation visualisation = new Visualisation();
        Visualisation.canvas = canvas;
        Visualisation.label = endLabel;

        queueLabel.setVisible(true);

        visualisation.initTables();
        visualisation.runVisualisation();
    }

    private void bindTextFields()
    {
        RestaurantFx restaurantFx = simulation.getRestaurantFx();


        NumberStringConverter converter = new NumberStringConverter();
        guestsNumber.textProperty().bindBidirectional(restaurantFx.guestNumberIntegerProperty(), converter);
        waitersNumber.textProperty().bindBidirectional(restaurantFx.waitersNumberIntegerProperty(), converter);
        mealsNumber.textProperty().bindBidirectional(restaurantFx.mealsNumberIntegerProperty(), converter);
        drinksNumber.textProperty().bindBidirectional(restaurantFx.drinksNumberIntegerProperty(), converter);
        cookNumber.textProperty().bindBidirectional(restaurantFx.cookNumberIntegerProperty(), converter);
    }
}
