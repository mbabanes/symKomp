package sk.sim.activity;

import deskit.SimActivity;
import sk.sim.RestaurantSimObject;
import sk.sim.object.Guest;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class NewGuestActivity extends SimActivity
{
    public static final int MAX_GUEST_NUMBER = 20;
    static Random random = new Random();
    RestaurantSimObject restaurantSimObject;

    private AtomicInteger counter = new AtomicInteger();

    @Override
    public void action()
    {
       while(true)
       {
           addGuest();
           if(counter.getAndIncrement() > MAX_GUEST_NUMBER)
           {
               callWaiters();
               waitDuration(1000);
               break;
           }
       }
    }

    private void addGuest()
    {
        restaurantSimObject = (RestaurantSimObject) getParentSimObject();

//            Thread.sleep(random.nextInt(600));
            waitDuration(random.nextInt(6000));
            Guest guest = new Guest();
            RestaurantSimObject.expectantGuests.addLast(guest);
            System.out.println("Nowy gosc w kolejce: " + guest);

            if (RestaurantSimObject.expectantGuests.size() > 6)
            {
                System.out.println("Nowi goscie stop");
                waitDuration(10000);
                callWaiters();
            }

    }

    private void callWaiters()
    {
        callActivity(restaurantSimObject.getWaiter(), restaurantSimObject.getTakingGuestActivity());
        callActivity(restaurantSimObject.getWaiter2(), restaurantSimObject.getTakingGuestActivity2());
    }
}
