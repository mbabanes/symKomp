package sk.sim.gui.controllers;

import deskit.SimManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sk.sim.RestaurantSimObject;

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
    public void startSimulationOnAction()
    {
        RestaurantSimObject restaurantSimObject = new RestaurantSimObject();

//        SimActivity.callActivity(restaurantSimObject, restaurantSimObject.getNewGuest());

        SimManager.getSimManager().setStopTime(200000000);

        System.out.println("Symulacja");
        SimManager.getSimManager().startSimulation();
        System.out.println("Koniec");

        System.out.println(RestaurantSimObject.expectantGuests.size());
    }
}
