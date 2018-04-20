package sk.sim.activity;

import deskit.SimActivity;
import sk.sim.RestaurantSimObject;
import sk.sim.object.WaiterSimObject;

public class GuestOutingActivity extends SimActivity
{
    private WaiterSimObject waiterSimObject;

    public GuestOutingActivity(WaiterSimObject waiterSimObject)
    {
        this.waiterSimObject = waiterSimObject;
    }

    @Override
    public void action()
    {
//        waitDuration(100);
        System.out.println(waiterSimObject.debugMessage() + " gość wyszedł");
        waiterSimObject.getCurrentGuestNumber().decrementAndGet();
        System.out.println(waiterSimObject.getCurrentGuestNumber().get());
    }

    public WaiterSimObject getWaiter()
    {
        return waiterSimObject;
    }
}
