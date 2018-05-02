package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;

import java.util.concurrent.CountDownLatch;

public class PlaceOrderActivity extends GuestVisitActivity
{
    public PlaceOrderActivity(GuestSimObject guest, CountDownLatch countDownLatch)
    {
        super(guest, countDownLatch);
    }

    public PlaceOrderActivity(GuestSimObject guest, CountDownLatch countDownLatch, Semaphore semaphore)
    {
        super(guest, countDownLatch, semaphore);
    }

    @Override
    public void action()
    {
        System.out.println(guest.debugMessage() + "Składa zamowienie");

        int time = random.nextInt(2000);
        waitDuration(time);
//        semaphore.signal();
        countDownLatch.countDown();
        System.out.println(guest.debugMessage() + "Złożył zamowienie.");

        OrderWaitingActivity activity = new OrderWaitingActivity(guest, countDownLatch, semaphore);
        callActivity(guest, activity);

//        semaphore.wait(activity);
    }
}
