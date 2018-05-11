package sk.sim.activities.waiters;

import deskit.SimActivity;
import deskit.synchronizers.Semaphore;
import sk.sim.activities.cook.OrderNote;
import sk.sim.activities.cook.OrderQueue;
import sk.sim.objects.OrderSimObject;
import sk.sim.utill.Logger;

public class OrderRealizationActivity extends SimActivity
{
    private OrderSimObject order;
    private Semaphore semaphore;

    public OrderRealizationActivity(OrderSimObject order, Semaphore semaphore)
    {
        this.order = order;
        this.semaphore = semaphore;
    }


    @Override
    public void action()
    {
        Semaphore semaphore = new Semaphore(-1);
        OrderNote orderNote = new OrderNote(order, semaphore);

        OrderQueue.addOrder(orderNote);
        Logger.log(order.debugMessage() +   "Przekazane do kolejki realizacji");
        semaphore.wait(this);
        waitOnSemaphore(semaphore);
        Logger.log(order.debugMessage() +  "Gotowe");
        callActivity(order.getGuestSimObject().getWaiterSimObject(), new BringingOrderForGuestActivity(order, this.semaphore));
        Logger.log(order.debugMessage() +   "Zamowienie zrealizowane");
    }
}
