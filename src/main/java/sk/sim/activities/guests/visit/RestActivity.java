package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;

import java.util.concurrent.CountDownLatch;

public class RestActivity extends GuestVisitActivity
{
    public RestActivity(GuestSimObject guest, CountDownLatch countDownLatch)
    {
        super(guest, countDownLatch);
    }

    public RestActivity(GuestSimObject guest, CountDownLatch countDownLatch, Semaphore semaphore)
    {
        super(guest, countDownLatch, semaphore);
    }

    @Override
    public void action()
    {
        System.out.println(guest.debugMessage() + "Odpoczywa po jedzeniu");

        int time = random.nextInt(700);
//        semaphore.signal();
        countDownLatch.countDown();
        System.out.println(guest.debugMessage() + "Skonczyl odpoczywać");

        OutingActivity activity = new OutingActivity(guest, countDownLatch, semaphore);
        callActivity(guest, activity);
//        semaphore.wait(activity);
    }
}
