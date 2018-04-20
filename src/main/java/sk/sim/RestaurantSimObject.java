package sk.sim;

import deskit.SimActivity;
import deskit.SimObject;
import lombok.Getter;
import sk.sim.activity.GuestOutingActivity;
import sk.sim.activity.NewGuestActivity;
import sk.sim.activity.TakingGuestActivity;
import sk.sim.object.Guest;
import sk.sim.object.WaiterSimObject;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

@Getter
public class RestaurantSimObject extends SimObject
{
    public static int WAITERS_NUMBER = 3;

    private static final Random random = new Random();

    public static Deque<Guest> expectantGuests = new LinkedBlockingDeque<>();
//    private static List<WaiterSimObject> waiters = new ArrayList<>();
    private static Map<WaiterSimObject, TakingGuestActivity> waiters = new HashMap<>();


//    private WaiterSimObject waiter;
//    private WaiterSimObject waiter2;
//
//
//    private TakingGuestActivity takingGuestActivity;
//    private TakingGuestActivity takingGuestActivity2;

    private SimActivity newGuest = new NewGuestActivity();

    public RestaurantSimObject()
    {
        prepareGuests();
        waitersInitialization();
//        this.waiter = new WaiterSimObject();
//        this.waiter2 = new WaiterSimObject();
//        this.takingGuestActivity = new TakingGuestActivity(waiter);
//        this.takingGuestActivity2 = new TakingGuestActivity(waiter2);
//        SimActivity.callActivity(this, takingGuestActivity);
//        SimActivity.callActivity(this, takingGuestActivity2);
        SimActivity.callActivity(this, newGuest);
    }

    private void prepareGuests()
    {
        expectantGuests.add(new Guest());
        expectantGuests.add(new Guest());
        expectantGuests.add(new Guest());
        expectantGuests.add(new Guest());
        expectantGuests.add(new Guest());
        expectantGuests.add(new Guest());
        expectantGuests.add(new Guest());
        expectantGuests.add(new Guest());
    }

    private void waitersInitialization()
    {
        for(int i = 0; i < WAITERS_NUMBER; i++)
        {
            WaiterSimObject waiter = new WaiterSimObject();
            TakingGuestActivity takingGuestActivity = new TakingGuestActivity(waiter);
            waiters.put(waiter, takingGuestActivity);
        }
    }

    public static void callWaiters()
    {
//        waiters.forEach(SimActivity::callActivity);

        Optional<WaiterSimObject> waiterSimObjectOptional = waiters.keySet().stream().filter(WaiterSimObject::isFree).findFirst();

        WaiterSimObject waiterSimObject = waiterSimObjectOptional.orElseGet(()->
        {
            int k = random.nextInt(WAITERS_NUMBER);
            Optional<WaiterSimObject> first = waiters.keySet().stream().filter(w -> w.getId() == k).findFirst();

            System.out.println(first.get().debugMessage() + " guestouting:");

            GuestOutingActivity activity = new GuestOutingActivity(first.get());
            SimActivity.callActivity(first.get(), activity);

            return activity.getWaiter();
        });

        System.out.println("Znalezlem wolnego" + waiterSimObject.debugMessage());
        SimActivity.callActivity(waiterSimObject, waiters.get(waiterSimObject));
    }

    public static boolean expectantGuestAreInRestaurant()
    {
        return ! expectantGuests.isEmpty();
    }
}
