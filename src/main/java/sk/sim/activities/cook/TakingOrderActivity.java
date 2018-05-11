package sk.sim.activities.cook;

import deskit.SimActivity;
import sk.sim.RestaurantSimObject;
import sk.sim.objects.CookSimObject;
import sk.sim.utill.Logger;

public class TakingOrderActivity extends SimActivity
{
    private CookSimObject cook;

    public TakingOrderActivity(CookSimObject cook)
    {
        this.cook = cook;
    }

    @Override
    public void action()
    {
        while (RestaurantSimObject.isOpened())
        {
            if (OrderQueue.orders.isEmpty())
            {
                waitDuration(200);
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
                    waitDuration(100);
                }
            }
        }
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
