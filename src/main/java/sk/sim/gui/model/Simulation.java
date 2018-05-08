package sk.sim.gui.model;

import deskit.SimManager;
import sk.sim.RestaurantSimObject;
import sk.sim.objects.Drink;
import sk.sim.objects.Meal;
import sk.sim.objects.Menu;
import sk.sim.objects.WaiterSimObject;
import sk.sim.utill.Logger;

import java.util.List;
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
        mealsAndDrinksStatistics();
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

    private void mealsAndDrinksStatistics()
    {
        Logger.log("\nSatatystyki zamowien:");

        Logger.log("Dania:");
        List<Meal> meals = Menu.getMeals();
        meals.forEach(meal -> Logger.log("[Meal " + meal.getId() + "] zamowiono: " + meal.getCounter()));

        Logger.log("Napoje:");
        List<Drink> drinks = Menu.getDrinks();
        drinks.forEach(drink -> Logger.log("[Drink " + drink.getId() + "] zamowiono: " + drink.getCounter()));
    }
}
