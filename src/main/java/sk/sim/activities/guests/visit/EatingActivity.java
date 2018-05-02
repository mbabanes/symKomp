package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;

import java.util.concurrent.CountDownLatch;

public class EatingActivity extends GuestVisitActivity
{
    public EatingActivity(GuestSimObject guest, CountDownLatch countDownLatch)
    {
        super(guest, countDownLatch);
    }

    public EatingActivity(GuestSimObject guest, CountDownLatch countDownLatch, Semaphore semaphore)
    {
        super(guest, countDownLatch, semaphore);
    }

    @Override
    public void action()
    {
        System.out.println(guest.debugMessage() + "Je");
        int time = random.nextInt(1300);
        waitDuration(time);

        countDownLatch.countDown();
//        semaphore.signal();


        System.out.println(guest.debugMessage() + "Skonczyl jesc.");

        RestActivity activity = new RestActivity(guest, countDownLatch, semaphore);
        callActivity(guest, activity);

//        semaphore.wait(activity);
    }
}
