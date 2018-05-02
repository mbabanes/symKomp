package sk.sim.activities.guests;

import deskit.SimActivity;
import deskit.synchronizers.Semaphore;
import deskit.synchronizers.Trigger;
import sk.sim.activities.guests.visit.PlaceOrderActivity;
import sk.sim.objects.GuestSimObject;

import java.util.concurrent.CountDownLatch;

public class GuestActivity extends SimActivity
{
    private GuestSimObject guest;

    private CountDownLatch countDownLatch;

    private Trigger trigger;

    public GuestActivity(GuestSimObject guest)
    {
        this.guest = guest;

        countDownLatch = new CountDownLatch(5);
    }

    @Override
    public void action()
    {
        System.out.println(guest.debugMessage() + " Rozpoczyna wizytę");

       Semaphore s = new Semaphore(-6);

        PlaceOrderActivity activity = new PlaceOrderActivity(guest, countDownLatch, s);
        callActivity(guest, activity);


        System.out.println("\n" + guest.debugMessage() + " semaforek\n");
        s.wait(activity);
        waitOnSemaphore(s);
//        while (countDownLatch.getCount() != 0)
//        {
//            waitDuration(1000);
//        }


        System.out.println(guest.debugMessage() + " Zakoncyl wizytę");
    }
}
