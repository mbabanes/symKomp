package sk.sim.activities.cook;

import deskit.SimActivity;
import sk.sim.RestaurantSimObject;
import sk.sim.objects.CookSimObject;
import sk.sim.utill.Context;
import sk.sim.utill.Logger;
import sk.sim.utill.Randomizer;

public class TakingOrderActivity extends SimActivity
{
    private CookSimObject cook;
    private Randomizer random = Context.getRandomizer();

    public TakingOrderActivity(CookSimObject cook)
    {
        this.cook = cook;
    }

    @Override
    public void action()
    {
        Logger.log(cook.debugMessage() + "wystartował");

        while (RestaurantSimObject.isOpened())
        {
            if (OrderQueue.orders.isEmpty())
            {
                takeShortBreak();
            }
            else
            {
                if (this.cook.isFree())
                {
                    OrderNote orderNote = OrderQueue.getFirstOrder();
                    prepare(orderNote);
                }
                else
                {
                    waitDuration(60);
                }
            }
        }
    }

    private void takeShortBreak()
    {
        waitDuration(random.nextInt(60));
    }

    private void prepare(OrderNote orderNote)
    {
        Logger.log(cook.debugMessage() + "przygotowuje zamowienie:" + orderNote.getOrder().getId());
        cook.takeOrder();

        startPreparing(orderNote);

    }

    private void startPreparing(OrderNote orderNote)
    {
        callActivity(orderNote.getOrder(), new PreparingActivity(cook, orderNote));
    }
}
