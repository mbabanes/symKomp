package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;

import java.util.concurrent.CountDownLatch;

public class RestActivity extends GuestVisitActivity
{
    public RestActivity(GuestSimObject guest, Semaphore semaphore)
    {
        super(guest, semaphore);
    }

    @Override
    public void action()
    {
        System.out.println(guest.debugMessage() + "Odpoczywa po jedzeniu");

        int time = random.nextInt(700);


        System.out.println(guest.debugMessage() + "Skonczyl odpoczywać");

        OutingActivity activity = new OutingActivity(guest, semaphore);
        callActivity(guest, activity);

    }
}
