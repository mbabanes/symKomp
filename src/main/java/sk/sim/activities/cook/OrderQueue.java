package sk.sim.activities.cook;

import sk.sim.objects.OrderSimObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class OrderQueue
{
    public static Deque<OrderNote> orders = new LinkedBlockingDeque<>();

    public static List<OrderSimObject> allOrders = Collections.synchronizedList(new ArrayList<>());

    public static OrderNote getFirstOrder()
    {
        return orders.removeFirst();
    }

    public static void addOrder(OrderNote order)
    {
        orders.addLast(order);
    }
}
