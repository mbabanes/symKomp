package sk.main;

import deskit.Application;
import deskit.SimActivity;
import deskit.SimManager;
import sk.entity.Order;
import sk.restaurant.Restaurant;
import sk.service.WaiterActivity;

public class Main extends Application
{
    public static void main(String[] args) throws InterruptedException
    {
//        Restaurant restaurant = new Restaurant();
//
//
//            restaurant.verySimpleSimulation();

        Restaurant restaurant = new Restaurant();

        SimActivity.callActivity(restaurant, restaurant.waiterActivity);
        SimManager.getSimManager().setStopTime(20000);
//        SimManager.getSimManager().startSimulation();
    }
}
