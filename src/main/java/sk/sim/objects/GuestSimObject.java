package sk.sim.objects;

import deskit.SimObject;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class GuestSimObject extends SimObject
{
    private static AtomicInteger ID = new AtomicInteger();

    private int id = ID.getAndIncrement();
    private WaiterSimObject waiterSimObject;
    private int time;

    private Order order;

    public GuestSimObject(int time)
    {
        this.time = time;
    }

    public void setWaiter(WaiterSimObject waiterSimObject)
    {
        this.waiterSimObject = waiterSimObject;
    }

    public void out()
    {
        System.out.println("Gość[" + id + "]: Wychodzi (" + waiterSimObject.debugMessage() + ")");
        waiterSimObject.getCurrentGuestNumber().decrementAndGet();
    }

    @Override
    public String toString()
    {
        return "GuestSimObject{" +
                "id=" + id +
                '}';
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public String debugMessage()
    {
        return "[Gość " + id + "]: ";
    }
}
