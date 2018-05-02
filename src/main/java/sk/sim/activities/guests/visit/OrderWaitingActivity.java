package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;

import java.util.concurrent.CountDownLatch;

public class OrderWaitingActivity extends GuestVisitActivity
{
    public OrderWaitingActivity(GuestSimObject guest, CountDownLatch countDownLatch)
    {
        super(guest, countDownLatch);
    }

    public OrderWaitingActivity(GuestSimObject guest, CountDownLatch countDownLatch, Semaphore semaphore)
    {
        super(guest, countDownLatch, semaphore);
    }

    @Override
    public void action()
    {
        System.out.println(guest.debugMessage() + "Oczekuje na zamowienie");
        int time = random.nextInt(2000);
        waitDuration(time);

        countDownLatch.countDown();
//        semaphore.signal();
        System.out.println(guest.debugMessage() + "Dostał zamowienie");

        EatingActivity activity = new EatingActivity(guest, countDownLatch, semaphore);
        callActivity(guest, activity);
//        semaphore.wait(activity);
    }
}
