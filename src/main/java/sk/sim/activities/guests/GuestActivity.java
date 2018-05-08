package sk.sim.activities.guests;

import deskit.SimActivity;
import deskit.synchronizers.Semaphore;
import sk.sim.activities.guests.visit.PlacingOrderActivity;
import sk.sim.objects.GuestSimObject;
import sk.sim.utill.Logger;

public class GuestActivity extends SimActivity
{
    private GuestSimObject guest;
    private Semaphore semaphore;


    public GuestActivity(GuestSimObject guest)
    {
        this.guest = guest;
        this.semaphore = new Semaphore(-1);
    }

    @Override
    public void action()
    {
        Logger.log(guest.debugMessage() + " Rozpoczyna wizytę");

        PlacingOrderActivity activity = new PlacingOrderActivity(guest, semaphore);
        callActivity(guest, activity);

        semaphore.wait(activity);
        waitOnSemaphore(semaphore);

        Logger.log(guest.debugMessage() + " Zakonczl wizytę");
    }
}
