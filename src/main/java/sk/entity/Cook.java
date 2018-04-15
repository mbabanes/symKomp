package sk.entity;

import deskit.SimObject;
import sk.restaurant.expection.CookTooBusyException;
import sk.util.Observable;
import sk.util.Observer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Cook
{
    public static int NUMBER;
    public static int MAX_NUMBER_OF_REQUESTS = 3;


    private Order currentOrder;

    private Deque<Order> ordersToPrepare = new LinkedList<>();
    private boolean free = true;


    public void addOrderToPrepare(Order order) throws CookTooBusyException
    {
        if(isTooBusy())
        {
            setFree(false);
            throw new CookTooBusyException();
        }
        else
            ordersToPrepare.addLast(order);
    }

    private boolean isTooBusy()
    {
        return ordersToPrepare.size() == MAX_NUMBER_OF_REQUESTS;
    }


    public void prepareOrder()
    {
        Order order = ordersToPrepare.getFirst();

        try
        {
            Thread.sleep(order.getTime());
            currentOrder = ordersToPrepare.removeFirst();
            setFree(true);
            shoutOrderDone();
        }
        catch (InterruptedException e)
        {

        }
    }

    private void shoutOrderDone()
    {
        currentOrder.getWaiter().giveOrder(currentOrder);
    }

    public void setFree(boolean free)
    {
        this.free = free;
    }
}
