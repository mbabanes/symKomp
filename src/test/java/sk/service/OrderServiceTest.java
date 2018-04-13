package sk.service;

import org.junit.Test;
import sk.entity.Guest;
import sk.entity.Meal;
import sk.entity.Order;
import sk.entity.Waiter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest
{
    @Test
    public void whenCreateOrderThenTimeIsCorrect()
    {
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        meals.add(new Meal(1));

        OrderService orderService = new OrderService();

        Order order = orderService.makeOrderFor(null, null, meals);

        assertEquals(4, order.getTime());
    }
}