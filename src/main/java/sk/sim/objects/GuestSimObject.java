package sk.sim.objects;

import deskit.SimObject;
import lombok.Getter;
import sk.sim.utill.Logger;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class GuestSimObject extends SimObject
{
    private static AtomicInteger ID = new AtomicInteger();

    private int id = ID.getAndIncrement();
    private WaiterSimObject waiterSimObject;
    private int time;

    private Duration timeOfVisit;
    private Duration timeOfWaitingForOrder;

    private OrderSimObject order;

    public GuestSimObject()
    {
    }


    public void setWaiter(WaiterSimObject waiterSimObject)
    {
        this.waiterSimObject = waiterSimObject;
    }

    public void out()
    {
        Logger.log("Gość[" + id + "]: Wychodzi (" + waiterSimObject.debugMessage() + ")");
        waiterSimObject.getCurrentGuestNumber().decrementAndGet();
    }

    @Override
    public String toString()
    {
        return "GuestSimObject{" +
                "id=" + id +
                '}';
    }

    public void setTimeOfVisit(Duration timeOfVisit)
    {
        this.timeOfVisit = timeOfVisit;
    }

    public void setTimeOfWaitingForOrder(Duration timeOfWaitingForOrder)
    {
        this.timeOfWaitingForOrder = timeOfWaitingForOrder;
    }

    public void setOrder(OrderSimObject order)
    {
        this.order = order;
    }

    public String debugMessage()
    {
        return "[Gość " + id + "]: ";
    }
}
