package sk.sim.objects;

import deskit.SimObject;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;


@Getter
public class CookSimObject extends SimObject
{
    public static int MAX_ORDER_REALESING = 5;

    private double stressRate = 0.1;

    private static AtomicInteger ID = new AtomicInteger(0);

    private AtomicInteger currentOrderNumber = new AtomicInteger(0);

    private AtomicInteger preparedOrdersNumber = new AtomicInteger(0);

    private int id;

    public CookSimObject()
    {
        this.id = ID.getAndIncrement();
    }


    public boolean isFree()
    {
        return currentOrderNumber.get() != MAX_ORDER_REALESING;
    }

    public void takeOrder()
    {
        currentOrderNumber.getAndIncrement();
        preparedOrdersNumber.getAndIncrement();
        increaseStressRate();
    }

    private synchronized void increaseStressRate()
    {
        stressRate += 0.1;
    }

    public String debugMessage()
    {
        return "[Cook " + id + "]: ";
    }
}
