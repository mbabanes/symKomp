package sk.sim.gui.model;

import deskit.SimManager;
import sk.sim.RestaurantSimObject;
import sk.sim.objects.WaiterSimObject;

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

        System.out.println("Symulacja");
        SimManager.getSimManager().startSimulation();
        System.out.println("Koniec");

        System.out.println(RestaurantSimObject.expectantGuests.size());

        waitersSatistic();
    }

    private void waitersSatistic()
    {
        Set<WaiterSimObject> waiters = RestaurantSimObject.getWaiters();
        System.out.println("\nStatystyki kelenrow:");
        waiters.forEach(waiter ->
                {
                    System.out.println(waiter.debugMessage() + " obsłużył:" + waiter.getGuestNumber());
                }
        );
    }
}
