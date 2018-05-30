package sk.sim.gui.model;

import deskit.SimManager;
import sk.sim.RestaurantSimObject;
import sk.sim.activities.cook.OrderQueue;
import sk.sim.objects.*;
import sk.sim.utill.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Simulation
{
    private RestaurantSimObject restaurantSimObject;
    private RestaurantFx restaurantFx;

    public Simulation()
    {
        restaurantFx = new RestaurantFx();
    }

    public RestaurantFx getRestaurantFx()
    {
        return restaurantFx;
    }

    public void start()
    {
        setMainPropertiesOfSimulation();

        restaurantSimObject = new RestaurantSimObject();


        startSimulation();
    }



    private void startSimulation()
    {
        Logger.log("Symulacja");
        SimManager.getSimManager().startSimulation();
        Logger.log("Koniec");

        Logger.log("Zamowienia: " + OrderQueue.orders.size());

        Logger.log("Gości w kolejce: " + Integer.toString(RestaurantSimObject.expectantGuests.size()));
    }

    private void setMainPropertiesOfSimulation()
    {
        RestaurantSimObject.GUEST_NUMBER = restaurantFx.getGuestNumber();
        RestaurantSimObject.WAITERS_NUMBER = restaurantFx.getWaitersNumber();
        RestaurantSimObject.MEALS_NUMBER = restaurantFx.getMealsNumber();
        RestaurantSimObject.DRINKS_NUMBER = restaurantFx.getDrinksNumber();
        RestaurantSimObject.COOKER_NUMBER = restaurantFx.getCookNumber();

        RestaurantSimObject.debugMainProperties();
        SimManager.getSimManager().setStopTime(200000000);
    }


}
