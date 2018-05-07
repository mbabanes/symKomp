package sk.sim.gui.model;

import deskit.SimManager;
import sk.sim.RestaurantSimObject;

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

        RestaurantSimObject restaurantSimObject = new RestaurantSimObject();

//        SimActivity.callActivity(restaurantSimObject, restaurantSimObject.getNewGuestCommingActivity());

        SimManager.getSimManager().setStopTime(200000000);

        System.out.println("Symulacja");
        SimManager.getSimManager().startSimulation();
        System.out.println("Koniec");

        System.out.println(RestaurantSimObject.expectantGuests.size());
    }
}
