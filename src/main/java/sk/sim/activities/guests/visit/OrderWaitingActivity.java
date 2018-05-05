package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;

import java.util.concurrent.CountDownLatch;

public class OrderWaitingActivity extends GuestVisitActivity
{
    public OrderWaitingActivity(GuestSimObject guest, Semaphore semaphore)
    {
        super(guest, semaphore);
    }

    @Override
    public void action()
    {
        long time = guest.getOrder().getPreparingTime();
        System.out.println(guest.debugMessage() + "Oczekuje na zamowienie (" + time + ").");
        waitDuration(time);


        System.out.println(guest.debugMessage() + "Dostał zamowienie");

        EatingActivity activity = new EatingActivity(guest, semaphore);
        callActivity(guest, activity);
    }
}
