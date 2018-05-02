package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;

import java.util.concurrent.CountDownLatch;

public class OutingActivity extends GuestVisitActivity
{
    public OutingActivity(GuestSimObject guest, CountDownLatch countDownLatch)
    {
        super(guest, countDownLatch);
    }

    public OutingActivity(GuestSimObject guest, CountDownLatch countDownLatch, Semaphore semaphore)
    {
        super(guest, countDownLatch, semaphore);
    }

    @Override
    public void action()
    {
        System.out.println(guest.debugMessage() + "Gość wychodzi");
        waitDuration(100);
        guest.out();

        countDownLatch.countDown();
        semaphore.signal();
        System.out.println(guest.debugMessage() + "Gość wyszedł");
    }
}
