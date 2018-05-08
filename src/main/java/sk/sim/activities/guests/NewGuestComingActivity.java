package sk.sim.activities.guests;

import deskit.SimActivity;
import sk.sim.RestaurantSimObject;
import sk.sim.objects.GuestSimObject;
import sk.sim.utill.Logger;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class NewGuestComingActivity extends SimActivity
{

    private static Random random = new Random();

    private AtomicInteger counter = new AtomicInteger();


    @Override
    public void action()
    {
        while (true)
        {
            addGuest();

            if (RestaurantSimObject.expectantGuests.size() > 6)
            {
                Logger.log("\nNowi goscie pause\n");
                waitDuration(10000);
            }


            if ( counter.getAndIncrement() > RestaurantSimObject.GUEST_NUMBER )
            {
                while( RestaurantSimObject.expectantGuestAreInRestaurant() )
                {
                    waitDuration(1000);
                }

                RestaurantSimObject.close();
                break;
            }
        }
    }

    private void addGuest()
    {
        GuestSimObject guest = new GuestSimObject();
        RestaurantSimObject.expectantGuests.addLast(guest);
        Logger.log("Nowy gosc w kolejce: " + guest);
    }
}
