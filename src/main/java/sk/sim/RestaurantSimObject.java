package sk.sim;

import deskit.SimActivity;
import deskit.SimObject;
import lombok.Getter;
import sk.sim.activity.NewGuestActivity;
import sk.sim.activity.TakingGuestActivity;
import sk.sim.object.Guest;
import sk.sim.object.WaiterSimObject;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

@Getter
public class RestaurantSimObject extends SimObject
{
    public static Deque<Guest> expectantGuests = new LinkedBlockingDeque<>();

    private WaiterSimObject waiter;
    private WaiterSimObject waiter2;

    private TakingGuestActivity takingGuestActivity;
    private TakingGuestActivity takingGuestActivity2;

    private SimActivity newGuest = new NewGuestActivity();

    public RestaurantSimObject()
    {
        prepareGuests();
        this.waiter = new WaiterSimObject();
        this.waiter2 = new WaiterSimObject();
        this.takingGuestActivity = new TakingGuestActivity(waiter);
        this.takingGuestActivity2 = new TakingGuestActivity(waiter2);
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
}
