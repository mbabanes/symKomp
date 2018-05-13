package sk.sim.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;
import sk.sim.RestaurantSimObject;
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
    private TextField cookNumber;

    @FXML
    private Button startButton;

    @FXML
    private TextArea logTextArea;

    @FXML
    private TextArea statsTextArea;

    @FXML
    private Canvas canvas;


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

        System.out.println(simulation.ordersStats());

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1);

        double startX = 246;
        double startY = 100;
        double spaceBeetTables = 80;
        double x = startX;
        double y = startY;
        for(int i = 1; i < (RestaurantSimObject.WAITERS_NUMBER*3 + 1); i++  )
        {
            gc.fillOval(x, y, 20, 20);
            x += 45;
            if(i % 6 == 0)
            {
                x = startX;
                y += spaceBeetTables;
            }
        }
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
