package sk.sim.objects;

import deskit.SimObject;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;


@Getter
public class CookSimObject extends SimObject
{
    public static int MAX_ORDER_REALSING = 5;

    private static AtomicInteger ID = new AtomicInteger(0);

    private AtomicInteger currentOrderNumber = new AtomicInteger(0);



    private int id;

    public CookSimObject()
    {
        this.id = ID.getAndIncrement();
    }


    public boolean isFree()
    {
        if (currentOrderNumber.get() == MAX_ORDER_REALSING)
        {
            return false;
        }

        return true;
    }

    public void takeOrder()
    {
        currentOrderNumber.getAndIncrement();
    }

    public String debugMessage()
    {
        return "[Cook " + id + "]: ";
    }
}
