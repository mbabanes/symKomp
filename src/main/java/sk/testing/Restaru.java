package sk.testing;

import deskit.SimActivity;
import deskit.SimObject;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class Restaru extends SimObject
{
    public static Deque<GuesObj> expectantGuests = new LinkedBlockingDeque<>();

    public static List<WaitSimOb> waiters = new ArrayList<>();


    public static AtomicBoolean opened = new AtomicBoolean(true);

    public Restaru()
    {
        this.prepareFirstGuests();
        waiters.add(new WaitSimOb());
        waiters.add(new WaitSimOb());
        SimActivity.callActivity(this, new NewGuestAct());
        waiters();
    }


    private void prepareFirstGuests()
    {
        expectantGuests.addFirst(new GuesObj());
        expectantGuests.addFirst(new GuesObj());
        expectantGuests.addFirst(new GuesObj());
        expectantGuests.addFirst(new GuesObj());
        expectantGuests.addFirst(new GuesObj());
        expectantGuests.addFirst(new GuesObj());
        expectantGuests.addFirst(new GuesObj());
        expectantGuests.addFirst(new GuesObj());
        expectantGuests.addFirst(new GuesObj());
        expectantGuests.addFirst(new GuesObj());
    }

    public static void waiters()
    {
        for (WaitSimOb w : waiters)
        {
            SimActivity.callActivity(w, new TakingAct(w));
        }
    }

}
