package sk.sim.activities.guests.visit;

import deskit.synchronizers.Semaphore;
import sk.sim.gui.visualisation.event.GuestRestEvent;
import sk.sim.gui.visualisation.log.VisualisationLog;
import sk.sim.objects.GuestSimObject;
import sk.sim.utill.Logger;

public class RestActivity extends GuestVisitActivity
{
    public RestActivity(GuestSimObject guest, Semaphore semaphore)
    {
        super(guest, semaphore);
    }

    @Override
    public void action()
    {
        VisualisationLog.log(guest.getId(), new GuestRestEvent());

        Logger.log(() -> guest.debugMessage() + "Odpoczywa po jedzeniu");

        int time = random.nextInt(700);


        Logger.log(() -> guest.debugMessage() + "Skonczyl odpoczywać");

        OutingActivity activity = new OutingActivity(guest, semaphore);
        callActivity(guest, activity);

    }
}
