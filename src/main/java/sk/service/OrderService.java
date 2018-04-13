package sk.service;

import sk.entity.*;

import java.util.List;

public class OrderService
{
    public Order makeOrderFor(Guest guest, Waiter waiter, List<Meal> meals)
    {
        Order order = new Order();
        order.setWaiter(waiter);
        order.setGuest(guest);
        order.setMeals(meals);

        int time = designateTime(meals);
        order.setTime(time);

        return order;
    }

    private int designateTime(List<Meal> meals)
    {
        return meals.stream()
                    .map(Meal::getTime)
                    .reduce(0, (a, b) -> a + b);
    }

    public void perepareOrder(Order order)
    {
        try
        {
            int time = (int)Math.ceil(order.getTime() / Cook.NUMBER );
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {

        }
    }
}
