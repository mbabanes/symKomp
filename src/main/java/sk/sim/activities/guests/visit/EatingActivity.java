package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.gui.visualisation.event.GuestEatingEvent;
import sk.sim.gui.visualisation.log.VisualisationLog;
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
        VisualisationLog.log(guest.getId(), new GuestEatingEvent());
        OrderSimObject order = guest.getOrder();
        guest.setTimeOfWaitingForOrder(Duration.between(order.getStartTime(), order.getReceiptTime()));

        Logger.log(guest.debugMessage() + "Je");
        int time = random.nextInt(1300);
        waitDuration(time);


//        semaphore.signal();


        Logger.log(guest.debugMessage() + "Skonczyl jesc.");

        RestActivity activity = new RestActivity(guest, semaphore);
        callActivity(guest, activity);

//        semaphore.wait(activity);
    }
}
