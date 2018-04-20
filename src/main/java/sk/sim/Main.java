package sk.sim;

import deskit.SimActivity;
import deskit.SimManager;

public class Main
{
    public static void main(String[] args)
    {
        RestaurantSimObject restaurantSimObject = new RestaurantSimObject();

//        SimActivity.callActivity(restaurantSimObject, restaurantSimObject.getNewGuest());

        SimManager.getSimManager().setStopTime(20000000);

        System.out.println("Symulacja");
        SimManager.getSimManager().startSimulation();
        System.out.println("Koniec");
    }
}
