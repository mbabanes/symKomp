package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.RestaurantSimObject;
import sk.sim.objects.GuestSimObject;
import sk.sim.objects.Menu;
import sk.sim.objects.Order;


public class PlacingOrderActivity extends GuestVisitActivity
{
    public PlacingOrderActivity(GuestSimObject guest, Semaphore semaphore)
    {
        super(guest, semaphore);
    }

    @Override
    public void action()
    {
        System.out.println(guest.debugMessage() + "Składa zamowienie");


        int time = designateWaitingTime();
        waitDuration(time);

        placeOrder();

        System.out.println(guest.debugMessage() + "Złożył zamowienie (m:" + guest.getOrder().getMealsNumber() + " |d:" + guest.getOrder().getDrinksNumber() + ").");

        OrderWaitingActivity activity = new OrderWaitingActivity(guest, semaphore);
        callActivity(guest, activity);
    }

    private int designateWaitingTime()
    {
        return (RestaurantSimObject.MEALS_NUMBER + RestaurantSimObject.DRINKS_NUMBER) * random.nextInt(15);
    }

    private void placeOrder()
    {
        Order order = new Order();

        chooseDrinks(order);
        chooseMeals(order);

        guest.setOrder(order);
    }

    private void chooseDrinks(Order order)
    {
        int numberOfDrinksToPrepare = random.nextInt(6) + 1;

        for(int i = 0; i < numberOfDrinksToPrepare; i++)
        {
            int drinkId = random.nextInt(RestaurantSimObject.DRINKS_NUMBER);
            order.addDrink(Menu.getDrink(drinkId));
        }
    }

    private void chooseMeals(Order order)
    {
        int numberOfMealsToPrepare = random.nextInt(6) + 1;
        for(int i = 0; i < numberOfMealsToPrepare; i++)
        {
            int mealId = random.nextInt(RestaurantSimObject.MEALS_NUMBER);
            order.addMeal(Menu.getMeal(mealId));
        }
    }
}
