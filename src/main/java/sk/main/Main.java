package sk.main;

import deskit.Application;
import deskit.SimManager;
import sk.restaurant.Restaurant;

public class Main extends Application
{
    public static void main(String[] args) //throws InterruptedException
    {
//        RestaurantSimObject restaurant = new RestaurantSimObject();
//
//
//            restaurant.verySimpleSimulation();

        Restaurant restaurant = new Restaurant();

//        SimActivity.callActivity(restaurant, restaurant.waiterActivity);
        SimManager.getSimManager().setStopTime(20000);
//        SimManager.getSimManager().startSimulation();

//        Thread.sleep(1000);


    }
}
