package sk.sim;

import deskit.SimActivity;
import deskit.SimObject;
import lombok.Getter;
import sk.sim.activities.NewGuestComingActivity;
import sk.sim.activities.TakingGuestActivity;
import sk.sim.objects.GuestSimObject;
import sk.sim.objects.WaiterSimObject;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class RestaurantSimObject extends SimObject
{
    private static final Random random = new Random();

    public static int WAITERS_NUMBER = 3;
    public static AtomicBoolean opened = new AtomicBoolean(true);


    public static Deque<GuestSimObject> expectantGuests = new LinkedBlockingDeque<>();

    private static Map<WaiterSimObject, TakingGuestActivity> waiters = new ConcurrentHashMap<>();

    public static List<GuestSimObject> servedGuests = Collections.synchronizedList(new ArrayList<>());


    private SimActivity newGuest;

    public RestaurantSimObject()
    {
        prepareFirstGuests();
        waitersInitialization();

        newGuest = new NewGuestComingActivity();

        SimActivity.callActivity(this, newGuest);
        this.callWaiters();
    }


    private void prepareFirstGuests()
    {
        expectantGuests.add(new GuestSimObject(random.nextInt(2300)));
        expectantGuests.add(new GuestSimObject(random.nextInt(2300)));
        expectantGuests.add(new GuestSimObject(random.nextInt(2300)));
        expectantGuests.add(new GuestSimObject(random.nextInt(2300)));
        expectantGuests.add(new GuestSimObject(random.nextInt(2300)));
        expectantGuests.add(new GuestSimObject(random.nextInt(2300)));
        expectantGuests.add(new GuestSimObject(random.nextInt(2300)));
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
}
