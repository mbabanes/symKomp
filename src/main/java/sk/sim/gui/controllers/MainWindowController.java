package sk.sim.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import sk.sim.RestaurantSimObject;
import sk.sim.gui.model.GuestsStatistics;
import sk.sim.gui.model.RestaurantFx;
import sk.sim.gui.model.Simulation;
import sk.sim.gui.visualisation.Visualisation;
import sk.sim.objects.GuestSimObject;
import sk.sim.objects.Menu;
import sk.sim.utill.Logger;

import java.util.Comparator;
import java.util.Deque;
import java.util.List;

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

    @FXML Button visualisationButton;

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

    @FXML
    private VBox chartVBox;

    private Simulation simulation;

    @FXML
    public void initialize()
    {
        simulation = new Simulation();

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

        putChart();
        visualisationButton.setVisible(true);
//        debugRestaurant();
    }

    @FXML
    public void runVisualisationOnAction()
    {
        runVisualisation();
        visualisationButton.setDisable(true);
    }

    @FXML
    public void printLogOnAction()
    {
        logTextArea.appendText(Logger.getLog());
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

    private void putChart()
    {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Liczba gości");


        xAxis.setTickLabelFormatter(new StringConverter<Number>()
        {
            @Override
            public String toString(Number object)
            {
                return Long.toString(object.longValue());
            }

            @Override
            public Number fromString(String string)
            {
                return 0;
            }
        });
        xAxis.setTickUnit(1);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Czaś wizyty");
        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);


        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        List<GuestSimObject> guests = RestaurantSimObject.servicedGuests;
        series.setName("Czas wizyty");
        guests.stream().sorted(Comparator.comparingInt(GuestSimObject::getId)).forEach(guest -> {
//            System.out.println(guest.debugMessage() + guest.getTimeOfVisit().toMillis());
            series.getData().add(new XYChart.Data<>(guest.getId(), guest.getTimeOfVisit()));
        });

        chart.getData().add(series);

        chart.setTitle("Liczba gości do czasu wizyty");

        chartVBox.getChildren().add(chart);
    }

    private void debugRestaurant()
    {
        RestaurantSimObject.getWaiters().forEach(waiter -> {
            System.out.println(waiter.debugMessage() + waiter.getStressRate());
        });

        RestaurantSimObject.getCookers().forEach(cook -> {
            System.out.println(cook.debugMessage() + cook.getStressRate());
        });

        Menu.getMeals().forEach(meal -> {
            System.out.println(meal.debugMessage() + meal.getPreparingTime());
        });

        Menu.getDrinks().forEach(drink -> {
            System.out.println(drink.debugMessage() + drink.getPreparingTime());
        });
    }
}
