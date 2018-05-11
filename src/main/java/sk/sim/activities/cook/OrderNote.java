package sk.sim.activities.cook;

import deskit.synchronizers.Semaphore;
import lombok.Getter;
import sk.sim.objects.OrderSimObject;

@Getter
public class OrderNote
{
    private OrderSimObject order;
    private Semaphore semaphore;

    public OrderNote(OrderSimObject order, Semaphore semaphore)
    {
        this.order = order;
        this.semaphore = semaphore;
    }
}
