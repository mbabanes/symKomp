package sk.testing;

import deskit.SimManager;

public class Main
{
    public static void main(String[] args)
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
