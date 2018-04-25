package sk.sim;

import deskit.SimActivity;
import deskit.SimObject;
import lombok.Getter;
import sk.sim.activity.NewGuestActivity;
import sk.sim.activity.TakingGuestActivity;
import sk.sim.exception.NoFreeWaiterException;
import sk.sim.object.GuestSimObject;
import sk.sim.object.WaiterSimObject;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class RestaurantSimObject extends SimObject
{
    public static int WAITERS_NUMBER = 3;

    private static final Random random = new Random();

    public static Deque<GuestSimObject> expectantGuests = new LinkedBlockingDeque<>();

    private static Map<WaiterSimObject, TakingGuestActivity> waiters = new ConcurrentHashMap<>();

    public static List<GuestSimObject> servedGuests = Collections.synchronizedList(new ArrayList<>());


    private SimActivity newGuest = new NewGuestActivity();

    public RestaurantSimObject()
    {
        prepareGuests();
        waitersInitialization();

        SimActivity.callActivity(this, newGuest);
    }

    public static void bidFarewellSomeGuest()
    {
        int g = random.nextInt(servedGuests.size());
        GuestSimObject guest = servedGuests.get(g);
        guest.out();
    }

    private void prepareGuests()
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

    public static void callWaiters() throws NoFreeWaiterException
    {
        Optional<WaiterSimObject> waiterSimObjectOptional = findFirsFreeWaiter();

        WaiterSimObject waiterSimObject = waiterSimObjectOptional.orElseThrow(NoFreeWaiterException::new);


        System.out.println("Znalezlem wolnego" + waiterSimObject.debugMessage());
        SimActivity.callActivity(waiterSimObject, waiters.get(waiterSimObject));
    }

    private static Optional<WaiterSimObject> findFirsFreeWaiter()
    {
        return waiters.keySet().stream()
                .filter(WaiterSimObject::isFree)
                .findFirst();
    }

    public static boolean expectantGuestAreInRestaurant()
    {
        return !expectantGuests.isEmpty();
    }
}
