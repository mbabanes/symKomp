package sk.sim.activities.waiters;

import deskit.SimActivity;
import deskit.synchronizers.Semaphore;
import sk.sim.activities.guests.visit.EatingActivity;
import sk.sim.objects.OrderSimObject;
import sk.sim.objects.WaiterSimObject;
import sk.sim.utill.Context;
import sk.sim.utill.Logger;
import sk.sim.utill.Randomizer;

public class BringingOrderForGuestActivity extends SimActivity
{
    private static Randomizer random = Context.getRandomizer();

    private OrderSimObject order;
    private Semaphore semaphore;

    public BringingOrderForGuestActivity(OrderSimObject order, Semaphore semaphore)
    {
        this.order = order;
        this.semaphore = semaphore;
    }

    @Override
    public void action()
    {
        WaiterSimObject waiter = order.getGuestSimObject().getWaiterSimObject();
        Logger.log(() ->waiter.debugMessage() + "Zanosi " + order.debugMessage() + "do " + order.getGuestSimObject().debugMessage());

        waitDuration(random.nextInt(1000) * waiter.getStressRate());

        order.setReceiptTime(order.getSimTime());

        callActivity(order.getGuestSimObject(), new EatingActivity(order.getGuestSimObject(), semaphore));

        waiter.setBusy(false);

        Logger.log(() -> waiter.debugMessage() + "Zaniósł " + order.debugMessage() + "do " + order.getGuestSimObject().debugMessage());
    }
}
