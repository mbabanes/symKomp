package sk.sim;

import deskit.SimActivity;
import deskit.SimObject;

import lombok.Getter;

import sk.sim.activities.cook.TakingOrderActivity;
import sk.sim.activities.guests.NewGuestComingActivity;
import sk.sim.activities.waiters.TakingGuestActivity;
import sk.sim.gui.visualisation.log.VisualisationLog;
import sk.sim.gui.visualisation.object.Guest;
import sk.sim.objects.*;
import sk.sim.utill.Logger;

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
    public static int COOKER_NUMBER = 3;

    public static AtomicBoolean opened = new AtomicBoolean(true);

    public static AtomicBoolean guestsComing = new AtomicBoolean(true);

    public static Deque<GuestSimObject> expectantGuests = new LinkedBlockingDeque<>();

    public static List<GuestSimObject> servicedGuests = Collections.synchronizedList(new ArrayList<>());

    private static Map<CookSimObject, TakingOrderActivity> cookres = new HashMap<>();
    private static Map<WaiterSimObject, TakingGuestActivity> waiters = new ConcurrentHashMap<>();


    private SimActivity newGuestComingActivity;

    public RestaurantSimObject()
    {
        initMealsAndDrinks();
//        prepareFirstGuests();
        waitersInitialization();
        cookerInitialization();

        newGuestComingActivity = new NewGuestComingActivity();

        SimActivity.callActivity(this, newGuestComingActivity);
        this.callWaiters();
        this.callCookers();
    }


    public static void newGuestCome(GuestSimObject guestSimObject)
    {
        RestaurantSimObject.expectantGuests.addLast(guestSimObject);
        Logger.log("Nowy gosc w kolejce: " + guestSimObject);

        Guest guestVisualisation = new Guest(guestSimObject);
        VisualisationLog.addNewGuest(guestVisualisation);
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
        for(int i = 0; i < 7; i++)
        {
            newGuestCome(new GuestSimObject());
        }
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

    private void cookerInitialization()
    {
        for(int i = 0; i < COOKER_NUMBER; i++)
        {
            CookSimObject cook = new CookSimObject();
            TakingOrderActivity takingOrderActivity = new TakingOrderActivity(cook);
            cookres.put(cook, takingOrderActivity);
        }
    }

    private void callWaiters()
    {
        waiters.forEach(SimActivity::callActivity);
    }

    private void callCookers()
    {
        cookres.forEach(SimActivity::callActivity);
    }


    public static boolean expectantGuestAreInRestaurant()
    {
        return !expectantGuests.isEmpty();
    }

    public static boolean isOpened()
    {
        return opened.get();
    }

    public static boolean areNewGuestComing()
    {
        return guestsComing.get();
    }

    public static void close()
    {
        opened.set(false);
    }

    public static void debugMainProperties()
    {
        Logger.log("Restaruant main properties:\nGuests:" + GUEST_NUMBER + " | Waiters:" + WAITERS_NUMBER + " | Cookers:" + COOKER_NUMBER + " | Meals:" + MEALS_NUMBER + " | Drinks:" + DRINKS_NUMBER + "\n");
    }

    public static Set<WaiterSimObject> getWaiters()
    {
        return waiters.keySet();
    }

    public static Set<CookSimObject> getCookers()
    {
        return cookres.keySet();
    }
}
