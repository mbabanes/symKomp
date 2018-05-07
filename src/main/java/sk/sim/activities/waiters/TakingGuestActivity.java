package sk.sim.activities.waiters;

import deskit.SimActivity;
import sk.sim.RestaurantSimObject;
import sk.sim.activities.guests.GuestActivity;
import sk.sim.objects.GuestSimObject;
import sk.sim.objects.WaiterSimObject;


public class TakingGuestActivity extends SimActivity
{
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

        while ( RestaurantSimObject.isOpened() )
        {
            if ( expectantGuestAreNotInRestaurant() )
            {
                takeShortBreak();
            }
            else
            {
                if ( this.waiter.isFree() )
                {
                    GuestSimObject guest = RestaurantSimObject.expectantGuests.removeFirst();
                    invite(guest);
//                    waitDuration(100);
                }
                else
                {
                    waitDuration(200);
                }
            }
        }
    }

    private boolean expectantGuestAreNotInRestaurant()
    {
        return ! RestaurantSimObject.expectantGuestAreInRestaurant();
    }

    private void takeShortBreak()
    {
        waitDuration(100);
    }


    private void invite(GuestSimObject guest)
    {
        System.out.println(debugMessage() + "Wzial goscia nr: " + guest.getId());
        guest.setWaiter(waiter);

        waiter.takeGuest();

        startVisitOf(guest);
    }

    private void startVisitOf(GuestSimObject guest)
    {
        callActivity(guest, new GuestActivity(guest));
    }

    private String debugMessage()
    {
        return "[Kelner " + waiter.getId() + "]:";
    }
}
