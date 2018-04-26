package sk.testing;

import deskit.SimObject;


public class GuesObj extends SimObject
{
    private static int ID = 0;

    public WaitSimOb waiter;

    public int id = ID++;


    public void out()
    {
        System.out.println("[Gosc" + id + "]: Wychodzi");
        waiter.guestsNumber.decrementAndGet();
    }


}
