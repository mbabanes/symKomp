package sk.sim.activities.guests;

import deskit.SimActivity;
import deskit.synchronizers.Semaphore;
import sk.sim.RestaurantSimObject;
import sk.sim.activities.guests.visit.PlacingOrderActivity;
import sk.sim.objects.GuestSimObject;
import sk.sim.utill.Logger;

import java.time.Duration;
import java.time.Instant;

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
        Instant start = Instant.now();
        Logger.log(guest.debugMessage() + " Rozpoczyna wizytę");

        PlacingOrderActivity activity = new PlacingOrderActivity(guest, semaphore);
        callActivity(guest, activity);

        semaphore.wait(activity);
        waitOnSemaphore(semaphore);

        Logger.log(guest.debugMessage() + " Zakonczl wizytę");
        Instant end = Instant.now();

        guest.setTimeOfVisit(Duration.between(start, end));

        RestaurantSimObject.servicedGuests.add(guest);
    }
}
