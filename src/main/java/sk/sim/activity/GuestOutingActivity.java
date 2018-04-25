package sk.sim.activity;

import deskit.SimActivity;
import sk.sim.object.GuestSimObject;
import sk.sim.object.WaiterSimObject;

public class GuestOutingActivity extends SimActivity
{
    private GuestSimObject guest;

    public GuestOutingActivity(GuestSimObject guest)
    {
        this.guest = guest;
    }

    @Override
    public void action()
    {
        waitDuration(guest.getTime());
        System.out.println(guest.getWaiterSimObject().debugMessage() + " gość wyszedł");
        guest.getWaiterSimObject().getCurrentGuestNumber().decrementAndGet();
    }
}
