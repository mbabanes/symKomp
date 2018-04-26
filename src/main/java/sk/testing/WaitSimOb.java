package sk.testing;

import deskit.SimObject;

import java.util.concurrent.atomic.AtomicInteger;

public class WaitSimOb extends SimObject
{
    public AtomicInteger guestsNumber = new AtomicInteger(0);

    public static int MAX = 3;

    public int id;

    public static int ID = 0;

    public WaitSimOb()
    {
        id = ID++;
    }

    public boolean takeGuest()
    {
        if (!(guestsNumber.get() == MAX))
        {
            System.out.println(debugMessageNarrow() + "Przyjąłem gościa");
            guestsNumber.incrementAndGet();
            return true;
        }

        System.out.println(debugMessageNarrow() + "Za duzo gosci naraz");
        return false;
    }

    public String debugMessageNarrow()
    {
        return "[Kelner" + id + "]: ";
    }
}
