package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;
import sk.sim.utill.Logger;

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
        Logger.log(guest.debugMessage() + "Odpoczywa po jedzeniu");

        int time = random.nextInt(700);


        Logger.log(guest.debugMessage() + "Skonczyl odpoczywać");

        OutingActivity activity = new OutingActivity(guest, semaphore);
        callActivity(guest, activity);

    }
}
