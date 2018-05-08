package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;
import sk.sim.utill.Logger;

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
        Logger.log(guest.debugMessage() + "Oczekuje na zamowienie (" + time + ").");
        waitDuration(time);


        Logger.log(guest.debugMessage() + "Dostał zamowienie");

        EatingActivity activity = new EatingActivity(guest, semaphore);
        callActivity(guest, activity);
    }
}
