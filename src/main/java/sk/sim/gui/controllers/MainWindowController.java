package sk.sim.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;
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

        statsTextArea.appendText("Statystyki kelnerów:\n");
        statsTextArea.appendText(simulation.waiterStatistics());

        statsTextArea.appendText("\nStatystyki kucharzy:\n");
        statsTextArea.appendText(simulation.cookersStatistics());

        statsTextArea.appendText("\nStatystyki zamówień:\n");
        statsTextArea.appendText(simulation.mealsAndDrinksStatistics());

        statsTextArea.appendText("\nStatystyki gości:\n");
        statsTextArea.appendText(simulation.guestsStats());

//        System.out.println(simulation.ordersStats());

        Visualisation visualisation = new Visualisation(canvas);
        visualisation.initTables();

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
