package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;

import java.util.concurrent.CountDownLatch;

public class EatingActivity extends GuestVisitActivity
{
    public EatingActivity(GuestSimObject guest, Semaphore semaphore)
    {
        super(guest, semaphore);
    }

    @Override
    public void action()
    {
        System.out.println(guest.debugMessage() + "Je");
        int time = random.nextInt(1300);
        waitDuration(time);


//        semaphore.signal();


        System.out.println(guest.debugMessage() + "Skonczyl jesc.");

        RestActivity activity = new RestActivity(guest, semaphore);
        callActivity(guest, activity);

//        semaphore.wait(activity);
    }
}
