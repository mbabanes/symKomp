package sk.sim.activities.cook;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class OrderQueue
{
    public static Deque<OrderNote> orders = new LinkedBlockingDeque<>();

    public static OrderNote getFirstOrder()
    {
        return orders.removeFirst();
    }

    public static void addOrder(OrderNote order)
    {
        orders.addLast(order);
    }
}
