package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.RestaurantSimObject;
import sk.sim.activities.cook.OrderQueue;
import sk.sim.activities.waiters.OrderRealizationActivity;
import sk.sim.gui.visualisation.event.GuestPlacingOrderEvent;
import sk.sim.gui.visualisation.event.GuestWaitingForOrder;
import sk.sim.gui.visualisation.log.VisualisationLog;
import sk.sim.objects.GuestSimObject;
import sk.sim.objects.Menu;
import sk.sim.objects.OrderSimObject;
import sk.sim.objects.WaiterSimObject;
import sk.sim.utill.Logger;


public class PlacingOrderActivity extends GuestVisitActivity
{
    public PlacingOrderActivity(GuestSimObject guest, Semaphore semaphore)
    {
        super(guest, semaphore);
    }

    @Override
    public void action()
    {
        VisualisationLog.log(guest.getId(), new GuestPlacingOrderEvent());
        Logger.log(() -> guest.debugMessage() + "Składa zamowienie");

        browseMenu();
        chooseMealAndDrink();

        Logger.log(() -> guest.debugMessage() + "Złożył zamowienie (m:" + guest.getOrder().getMealsNumber() + " |d:" + guest.getOrder().getDrinksNumber() + ").");

        waitForWaiter();
        VisualisationLog.log(guest.getId(), new GuestWaitingForOrder());

        Logger.log(() -> guest.debugMessage() + "Oczekuje na zamowienie.");
    }

    private void browseMenu()
    {
        int time = designateWaitingTimeOfPlacingOrder();
        waitDuration(time);
    }

    private void chooseMealAndDrink()
    {
        OrderSimObject order = new OrderSimObject();

        chooseDrinks(order);
        chooseMeals(order);

        OrderQueue.allOrders.add(order);

        guest.setOrder(order);
        order.setGuest(guest);

    }

    private void waitForWaiter()
    {
        guest.getOrder().setStartTime(guest.getSimTime());
        WaiterSimObject waiter = guest.getWaiterSimObject();
        while (waiter.isBusy())
        {
            waitDuration(1000);
            Logger.consoleLog(() -> waiter.debugMessage() + " zbyt zajety");
        }
        waiter.setBusy(true);

        waitDuration(random.nextInt(1000) * waiter.getStressRate());
        Logger.consoleLog(() -> waiter.debugMessage() + " przyjal zamowienie i zanosi do realizacji");
        callActivity(waiter, new OrderRealizationActivity(guest.getOrder(), semaphore));
    }

    private int designateWaitingTimeOfPlacingOrder()
    {
        return (RestaurantSimObject.MEALS_NUMBER + RestaurantSimObject.DRINKS_NUMBER) * random.nextInt(15);
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
