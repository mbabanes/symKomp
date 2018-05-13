package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.RestaurantSimObject;
import sk.sim.activities.waiters.OrderRealizationActivity;
import sk.sim.objects.GuestSimObject;
import sk.sim.objects.Menu;
import sk.sim.objects.OrderSimObject;
import sk.sim.objects.WaiterSimObject;
import sk.sim.utill.Logger;

import java.time.Instant;


public class PlacingOrderActivity extends GuestVisitActivity
{
    public PlacingOrderActivity(GuestSimObject guest, Semaphore semaphore)
    {
        super(guest, semaphore);
    }

    @Override
    public void action()
    {
        Logger.log(guest.debugMessage() + "Składa zamowienie");

        browseMenu();
        placeOrder();

        Logger.log(guest.debugMessage() + "Złożył zamowienie (m:" + guest.getOrder().getMealsNumber() + " |d:" + guest.getOrder().getDrinksNumber() + ").");

        callWaiterToRealizationOrder();
        Logger.log(guest.debugMessage() + "Oczekuje na zamowienie.");
    }

    private void browseMenu()
    {
        int time = designateWaitingTimeOfPlacingOrder();
        waitDuration(time);
    }

    private int designateWaitingTimeOfPlacingOrder()
    {
        return (RestaurantSimObject.MEALS_NUMBER + RestaurantSimObject.DRINKS_NUMBER) * random.nextInt(15);
    }

    private void placeOrder()
    {
        OrderSimObject order = new OrderSimObject();

        chooseDrinks(order);
        chooseMeals(order);

        guest.setOrder(order);
    }

    private void callWaiterToRealizationOrder()
    {
        WaiterSimObject waiter = guest.getWaiterSimObject();

        guest.getOrder().setGuest(guest);

        guest.getOrder().setStartTime(Instant.now());

        callActivity(waiter, new OrderRealizationActivity(guest.getOrder(), semaphore));
    }

    private void chooseDrinks(OrderSimObject order)
    {
        int numberOfDrinksToPrepare = random.nextInt(6) + 1;

        for(int i = 0; i < numberOfDrinksToPrepare; i++)
        {
            int drinkId = random.nextInt(RestaurantSimObject.DRINKS_NUMBER);
            order.addDrink(Menu.getDrink(drinkId));
        }
    }

    private void chooseMeals(OrderSimObject order)
    {
        int numberOfMealsToPrepare = random.nextInt(6) + 1;
        for(int i = 0; i < numberOfMealsToPrepare; i++)
        {
            int mealId = random.nextInt(RestaurantSimObject.MEALS_NUMBER);
            order.addMeal(Menu.getMeal(mealId));
        }
    }
}
