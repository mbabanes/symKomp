package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;
import sk.sim.objects.OrderSimObject;
import sk.sim.utill.Logger;

import java.time.Duration;

public class EatingActivity extends GuestVisitActivity
{
    public EatingActivity(GuestSimObject guest, Semaphore semaphore)
    {
        super(guest, semaphore);
    }

    @Override
    public void action()
    {
        Logger.log(guest.debugMessage() + "Je");
        int time = random.nextInt(1300);
        waitDuration(time);

        OrderSimObject order = guest.getOrder();

        guest.setTimeOfWaitingForOrder(Duration.between(order.getStartTime(), order.getReceiptTime()));
//        semaphore.signal();


        Logger.log(guest.debugMessage() + "Skonczyl jesc.");

        RestActivity activity = new RestActivity(guest, semaphore);
        callActivity(guest, activity);

//        semaphore.wait(activity);
    }
}
