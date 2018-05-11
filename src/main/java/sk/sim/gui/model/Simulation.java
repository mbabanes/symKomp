package sk.sim.gui.model;

import deskit.SimManager;
import sk.sim.RestaurantSimObject;
import sk.sim.activities.cook.OrderQueue;
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
        setMainPropertiesOfSimulation();

        restaurantSimObject = new RestaurantSimObject();


        startSimulation();
    }

    public String waiterStatistics()
    {
        StringBuilder stringBuilder = prepareWaitersStatistic();

        return stringBuilder.toString();
    }

    public String mealsAndDrinksStatistics()
    {
        StringBuilder stringBuilder = new StringBuilder();

        prepareMealsStatsAndSaveIn(stringBuilder);
        prepareDrinksStatsAndSaveIn(stringBuilder);


        return stringBuilder.toString();
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

        RestaurantSimObject.debugMainProperties();
        SimManager.getSimManager().setStopTime(200000000);
    }

    private StringBuilder prepareWaitersStatistic()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Set<WaiterSimObject> waiters = RestaurantSimObject.getWaiters();
        waiters.forEach(waiter ->
                {
                    stringBuilder.append(waiter.debugMessage())
                            .append(" obsłużył:")
                            .append(waiter.getGuestNumber())
                            .append("\n");
                }
        );
        return stringBuilder;
    }

    private void prepareMealsStatsAndSaveIn(StringBuilder stringBuilder)
    {
        stringBuilder.append("Dania:\n");
        List<Meal> meals = Menu.getMeals();
        meals.forEach(meal -> {
            stringBuilder.append("[Meal ")
                    .append(meal.getId())
                    .append("] zamowiono: ")
                    .append(meal.getCounter())
                    .append('\n');
        });
    }

    private void prepareDrinksStatsAndSaveIn(StringBuilder stringBuilder)
    {
        stringBuilder.append("Napoje:\n");
        List<Drink> drinks = Menu.getDrinks();
        drinks.forEach(drink ->{
            stringBuilder.append("[Drink ")
                    .append(drink.getId())
                    .append("] zamowiono: ")
                    .append(drink.getCounter())
                    .append('\n');
        });
    }
}
