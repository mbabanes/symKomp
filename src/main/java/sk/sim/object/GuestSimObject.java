package sk.sim.object;

import deskit.SimActivity;
import deskit.SimManager;
import deskit.SimObject;
import lombok.Getter;
import sk.sim.activity.GuestOutingActivity;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class GuestSimObject extends SimObject
{

    private static AtomicInteger ID = new AtomicInteger();

    private int id = ID.getAndIncrement();

    private WaiterSimObject waiterSimObject;

    private int time;

    public GuestSimObject(int time)
    {
        this.time = time;
    }

    public void setWaiterSimObject(WaiterSimObject waiterSimObject)
    {
        this.waiterSimObject = waiterSimObject;
    }

    public void out()
    {
        SimActivity outing = new GuestOutingActivity(this);

        System.out.println("wychodzi gosc: " + id + " po czasie " + time);
        SimActivity.callActivity(this, outing);
    }

    @Override
    public String toString()
    {
        return "GuestSimObject{" +
                "id=" + id +
                '}';
    }
}
