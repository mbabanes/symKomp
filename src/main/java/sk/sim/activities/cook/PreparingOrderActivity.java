package sk.sim.activities.cook;

import deskit.SimActivity;
import sk.sim.objects.CookSimObject;
import sk.sim.utill.Logger;

public class PreparingOrderActivity extends SimActivity
{
    private CookSimObject cook;
    private OrderNote orderNote;


    public PreparingOrderActivity(CookSimObject cook, OrderNote orderNote)
    {
        this.cook = cook;
        this.orderNote = orderNote;
    }

    public PreparingOrderActivity(OrderNote orderNote)
    {
        this.orderNote = orderNote;
    }

    @Override
    public void action()
    {
        Logger.log(() -> cook.debugMessage() + orderNote.getOrder().debugMessage() + "W fazie przygotowania");
        double preparingTime = orderNote.getOrder().getPreparingTime() * cook.getStressRate();
        waitDuration(preparingTime);

        Logger.log(() -> cook.debugMessage() + orderNote.getOrder().debugMessage() + "Przygotowywanie skonczone.");
        cook.getCurrentOrderNumber().decrementAndGet();
        orderNote.getOrder().done.set(true);
        orderNote.getSemaphore().signal();
    }
}
