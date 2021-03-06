package sk.sim.activities.guests;

import deskit.SimActivity;
import deskit.synchronizers.Semaphore;
import sk.sim.RestaurantSimObject;
import sk.sim.activities.guests.visit.PlacingOrderActivity;
import sk.sim.gui.visualisation.event.GuestSitingDownEvent;
import sk.sim.gui.visualisation.log.VisualisationLog;
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
        VisualisationLog.log(guest.getId(), new GuestSitingDownEvent());
        double startTime  = guest.getSimTime();

        Logger.log(() -> guest.debugMessage() + " Rozpoczyna wizytę");

        sitDown();
        startVisit();

        Logger.log(() -> guest.debugMessage() + " Zakonczl wizytę");

        double endTime = guest.getSimTime();
        double timeOfVisit = endTime - startTime;
        guest.setTimeOfVisit(timeOfVisit);

        RestaurantSimObject.servicedGuests.add(guest);

        Logger.consoleLog(() -> guest.getWaiterSimObject().debugMessage() + "st:" + guest.getWaiterSimObject().getStressRate());

        calculateTimeOfWaitingForOrder();
    }

    private void sitDown()
    {
        waitDuration(100);
    }

    private void startVisit()
    {
        PlacingOrderActivity activity = placeOrder();
        semaphore.wait(activity);
        waitOnSemaphore(semaphore);
    }

    private void calculateTimeOfWaitingForOrder()
    {
        guest.setTimeOfWaitingForOrder(guest.getOrder().getReceiptTime() - guest.getOrder().getStartTime());
    }

    private PlacingOrderActivity placeOrder()
    {
        PlacingOrderActivity activity = new PlacingOrderActivity(guest, semaphore);
        callActivity(guest, activity);
        return activity;
    }
}
