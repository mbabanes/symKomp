package sk.sim.gui.model;

import deskit.SimManager;
import sk.sim.RestaurantSimObject;
import sk.sim.objects.WaiterSimObject;
import sk.sim.utill.Logger;

import java.util.Set;

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
        RestaurantSimObject.GUEST_NUMBER = restaurantFx.getGuestNumber();
        RestaurantSimObject.WAITERS_NUMBER = restaurantFx.getWaitersNumber();
        RestaurantSimObject.MEALS_NUMBER = restaurantFx.getMealsNumber();
        RestaurantSimObject.DRINKS_NUMBER = restaurantFx.getDrinksNumber();

        RestaurantSimObject.debugMainProperties();

        restaurantSimObject = new RestaurantSimObject();

//        SimActivity.callActivity(restaurantSimObject, restaurantSimObject.getNewGuestCommingActivity());

        SimManager.getSimManager().setStopTime(200000000);

        Logger.log("Symulacja");
        SimManager.getSimManager().startSimulation();
        Logger.log("Koniec");

        Logger.log(Integer.toString(RestaurantSimObject.expectantGuests.size()));

        waiterStatistics();
    }

    private void waiterStatistics()
    {
        Set<WaiterSimObject> waiters = RestaurantSimObject.getWaiters();
        Logger.log("\nStatystyki kelenrow:");
        waiters.forEach(waiter ->
                {
                    Logger.log(waiter.debugMessage() + " obsłużył:" + waiter.getGuestNumber());
                }
        );
    }
}
