package sk.testing.gui;

import deskit.SimManager;
import javafx.fxml.FXML;
import sk.testing.Restaru;

public class Controller
{

    @FXML
    public void onAction()
    {
        Restaru restaurantSimObject = new Restaru();

//        SimActivity.callActivity(restaurantSimObject, restaurantSimObject.getNewGuest());

        SimManager.getSimManager().setStopTime(2000000000);

        System.out.println("Symulacja");
        SimManager.getSimManager().startSimulation();
        System.out.println("Koniec");

        System.out.println(Restaru.expectantGuests.size());
    }
}
