package sk.sim.activity;

import deskit.SimActivity;
import sk.sim.RestaurantSimObject;
import sk.sim.exception.NoFreeWaiterException;
import sk.sim.object.GuestSimObject;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class NewGuestActivity extends SimActivity
{
    public static final int MAX_GUEST_NUMBER = 20;
    static Random random = new Random();

    private AtomicInteger counter = new AtomicInteger();

    @Override
    public void action()
    {
        while (true)
        {
            addGuest();

            if (RestaurantSimObject.expectantGuests.size() > 6)
            {
                System.out.println("\nNowi goscie stop\n");
                waitDuration(10000);
                callWaiters();
            }


            if ((counter.getAndIncrement() > MAX_GUEST_NUMBER))
            {
                while(RestaurantSimObject.expectantGuestAreInRestaurant())
                {
                    waitDuration(1000);
                    callWaiters();
                }
                break;
            }
        }
    }

    private void addGuest()
    {
        GuestSimObject guest = new GuestSimObject(random.nextInt(2300));
        RestaurantSimObject.expectantGuests.addLast(guest);
        System.out.println("Nowy gosc w kolejce: " + guest);
    }

    private void callWaiters()
    {
        try
        {
            RestaurantSimObject.callWaiters();
        }
        catch (NoFreeWaiterException e)
        {
            RestaurantSimObject.bidFarewellSomeGuest();
        }
    }
}
