package sk.sim.activity;

import deskit.SimActivity;
import sk.sim.RestaurantSimObject;
import sk.sim.object.Guest;
import sk.sim.object.WaiterSimObject;

import java.util.Random;

public class TakingGuestActivity extends SimActivity
{
    private static Random random = new Random();
    private WaiterSimObject waiter;

    public TakingGuestActivity(WaiterSimObject waiterSimObject)
    {
        this.waiter = waiterSimObject;
    }

    @Override
    public void action()
    {
        System.out.print("\n" + debugMessage());
        System.out.println("[Thread TakingGuestActivity]: " + super.getName() + super.getId());

        while (RestaurantSimObject.expectantGuestAreInRestaurant())
        {
            Guest guest = RestaurantSimObject.expectantGuests.getFirst();
            if (this.takeGuest(guest.getId()))
            {
                RestaurantSimObject.expectantGuests.removeFirst();
            } else
            {
                RestaurantSimObject.callWaiters();
                break;
            }
        }
    }


    private void waitForFreeWaiter()
    {
        long time = random.nextInt(5000);
        waitDuration(time);
        guestLeave();
    }

    private boolean takeGuest(int idGuest)
    {
        if (waiter.isFree())
        {
            System.out.println(debugMessage() + "Wzial goscia nr: " + idGuest);
            waiter.getCurrentGuestNumber().incrementAndGet();
            return true;
        } else return false;
    }

    private void guestLeave()
    {
        System.out.println(debugMessage() + " gość wyszedł");
        waiter.getCurrentGuestNumber().decrementAndGet();
    }

    private String debugMessage()
    {
        return "[Kelner " + waiter.getId() + "]:";
    }
}
