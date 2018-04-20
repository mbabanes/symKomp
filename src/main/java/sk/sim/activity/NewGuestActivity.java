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

    private AtomicInteger counter = new AtomicInteger();

    @Override
    public void action()
    {
       while(true)
       {
           addGuest();

           if (RestaurantSimObject.expectantGuests.size() > 6)
           {
               System.out.println("\nNowi goscie stop\n");
               waitDuration(10000);
               RestaurantSimObject.callWaiters();
           }


           if( (counter.getAndIncrement() > MAX_GUEST_NUMBER) && ( RestaurantSimObject.expectantGuestAreInRestaurant() ) )
           {
               RestaurantSimObject.callWaiters();
               break;
           }
       }
    }

    private void addGuest()
    {
//        restaurantSimObject = (RestaurantSimObject) getParentSimObject();

//            Thread.sleep(random.nextInt(600));
            waitDuration(random.nextInt(6000));
            Guest guest = new Guest();
            RestaurantSimObject.expectantGuests.addLast(guest);
            System.out.println("Nowy gosc w kolejce: " + guest);

    }
}
