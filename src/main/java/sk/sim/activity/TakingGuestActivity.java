package sk.sim.activity;

import deskit.SimActivity;
import sk.sim.RestaurantSimObject;
import sk.sim.object.GuestSimObject;
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

        while ( this.waiter.isFree() )
        {
            if( RestaurantSimObject.expectantGuestAreInRestaurant() )
            {
                GuestSimObject guest = RestaurantSimObject.expectantGuests.removeFirst();
                this.takeGuest(guest);
            }
            else break;
        }
    }


    private void takeGuest(GuestSimObject guest)
    {
        System.out.println(debugMessage() + "Wzial goscia nr: " + guest.getId());
        guest.setWaiterSimObject(waiter);
        RestaurantSimObject.servedGuests.add(guest);
        waiter.getCurrentGuestNumber().incrementAndGet();
    }

    private String debugMessage()
    {
        return "[Kelner " + waiter.getId() + "]:";
    }
}
