package sk.sim.activities.guests;

import deskit.SimActivity;
import sk.sim.RestaurantSimObject;
import sk.sim.activities.cook.OrderQueue;
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
            waitDuration(1000);
            if (RestaurantSimObject.expectantGuests.size() > 6)
            {
                Logger.log(() -> "\nNowi goscie pause\n");
                waitDuration(100000);
            }


            if ( counter.getAndIncrement() > RestaurantSimObject.GUEST_NUMBER )
            {
                while( RestaurantSimObject.expectantGuestAreInRestaurant() )
                {
                    waitDuration(1000);
                }
                stopNewGuestComing();

                while(areOrdersInProgress())
                {
                    waitDuration(1000);
                }

                RestaurantSimObject.close();
                break;
            }
        }

        waitDuration(20000);
    }

    private void addGuest()
    {
       RestaurantSimObject.newGuestCome(new GuestSimObject());
    }

    private void stopNewGuestComing()
    {
        RestaurantSimObject.guestsComing.set(false);
    }

    private boolean areOrdersInProgress()
    {
        return OrderQueue.orders.size() != 0;
    }
}
