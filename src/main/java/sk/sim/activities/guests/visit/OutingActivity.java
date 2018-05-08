package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;
import sk.sim.utill.Logger;

import java.util.concurrent.CountDownLatch;

public class OutingActivity extends GuestVisitActivity
{
    public OutingActivity(GuestSimObject guest, Semaphore semaphore)
    {
        super(guest, semaphore);
    }

    @Override
    public void action()
    {
        Logger.log(guest.debugMessage() + "Gość wychodzi");
        waitDuration(100);
        guest.out();

        semaphore.signal();
        Logger.log(guest.debugMessage() + "Gość wyszedł");
    }
}
