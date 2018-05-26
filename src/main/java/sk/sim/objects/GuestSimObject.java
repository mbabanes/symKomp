package sk.sim.objects;

import deskit.SimObject;
import lombok.Getter;
import lombok.Setter;
import sk.sim.objects.utill.WaitingTimeInQueueCalculator;
import sk.sim.utill.Logger;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class GuestSimObject extends SimObject
{
    private static AtomicInteger ID = new AtomicInteger();

    private int id = ID.getAndIncrement();
    private WaiterSimObject waiterSimObject;
    private int time;


    private double orderTime;


    private WaitingTimeInQueueCalculator waitingTimeInQueueCalculator;

    private double timeOfVisit;
    private double timeOfWaitingForOrder;

    private OrderSimObject order;

    public GuestSimObject()
    {
        waitingTimeInQueueCalculator = new WaitingTimeInQueueCalculator(getSimTime());
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



    public double getWaitingTimeInQueue()
    {
        return waitingTimeInQueueCalculator.calculateTimeInQueue();
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

