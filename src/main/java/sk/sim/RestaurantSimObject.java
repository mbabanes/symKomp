package sk.sim;

import deskit.SimActivity;
import deskit.SimObject;
import lombok.Getter;
import sk.sim.activities.guests.NewGuestComingActivity;
import sk.sim.activities.waiters.TakingGuestActivity;
import sk.sim.objects.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class RestaurantSimObject extends SimObject
{
    public static int GUEST_NUMBER = 20;
    public static int WAITERS_NUMBER = 3;
    public static int MEALS_NUMBER = 10;
    public static int DRINKS_NUMBER = 5;

    public static AtomicBoolean opened = new AtomicBoolean(true);

    public static Deque<GuestSimObject> expectantGuests = new LinkedBlockingDeque<>();

    private static Map<WaiterSimObject, TakingGuestActivity> waiters = new ConcurrentHashMap<>();


    private SimActivity newGuestComingActivity;

    public RestaurantSimObject()
    {
        initMealsAndDrinks();
        prepareFirstGuests();
        waitersInitialization();

        newGuestComingActivity = new NewGuestComingActivity();

        SimActivity.callActivity(this, newGuestComingActivity);
        this.callWaiters();
    }


    private void initMealsAndDrinks()
    {
        for(int i = 0; i < MEALS_NUMBER; i++)
        {
            Menu.addMeal(new Meal());
        }

        for(int i = 0; i < DRINKS_NUMBER; i++)
        {
            Menu.addDrink(new Drink());
        }
    }

    private void prepareFirstGuests()
    {
        expectantGuests.add(new GuestSimObject());
        expectantGuests.add(new GuestSimObject());
        expectantGuests.add(new GuestSimObject());
        expectantGuests.add(new GuestSimObject());
        expectantGuests.add(new GuestSimObject());
        expectantGuests.add(new GuestSimObject());
    }

    private void waitersInitialization()
    {
        for (int i = 0; i < WAITERS_NUMBER; i++)
        {
            WaiterSimObject waiter = new WaiterSimObject();
            TakingGuestActivity takingGuestActivity = new TakingGuestActivity(waiter);
            waiters.put(waiter, takingGuestActivity);
        }
    }

    private void callWaiters()
    {
        waiters.forEach(SimActivity::callActivity);
    }


    public static boolean expectantGuestAreInRestaurant()
    {
        return !expectantGuests.isEmpty();
    }

    public static boolean isOpened()
    {
        return opened.get();
    }

    public static void close()
    {
        opened.set(false);
    }

    public static void debugMainProperties()
    {
        System.out.println("Restaruant main properties:\nGuests:" + GUEST_NUMBER + " | Waiters:" + WAITERS_NUMBER + " | Meals:" + MEALS_NUMBER + " | Drinks:" + DRINKS_NUMBER + "\n");
    }

    public static Set<WaiterSimObject> getWaiters()
    {
        return waiters.keySet();
    }
}
