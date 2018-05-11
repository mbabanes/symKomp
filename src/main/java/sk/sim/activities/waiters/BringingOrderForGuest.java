package sk.sim.activities.waiters;

import deskit.SimActivity;
import deskit.synchronizers.Semaphore;
import sk.sim.activities.guests.visit.EatingActivity;
import sk.sim.objects.OrderSimObject;
import sk.sim.utill.Logger;

public class BringingOrderForGuest extends SimActivity
{

    private OrderSimObject order;
    private Semaphore semaphore;

    public BringingOrderForGuest(OrderSimObject order, Semaphore semaphore)
    {
        this.order = order;
        this.semaphore = semaphore;
    }

    @Override
    public void action()
    {
        Logger.log(order.getGuestSimObject().getWaiterSimObject().debugMessage() + "Zanosi " + order.debugMessage() + "do " + order.getGuestSimObject().debugMessage());
        callActivity(order.getGuestSimObject(), new EatingActivity(order.getGuestSimObject(), semaphore));
        Logger.log(order.getGuestSimObject().getWaiterSimObject().debugMessage() + "Zaniósł " + order.debugMessage() + "do " + order.getGuestSimObject().debugMessage());
    }
}
