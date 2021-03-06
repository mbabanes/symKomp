package sk.sim.activities.guests.visit;

import deskit.SimActivity;
import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;
import sk.sim.utill.Context;
import sk.sim.utill.Randomizer;

import java.util.concurrent.CountDownLatch;

abstract public class GuestVisitActivity extends SimActivity
{
    protected static Randomizer random = Context.getRandomizer();

    protected GuestSimObject guest;


    protected Semaphore semaphore;

    public GuestVisitActivity(GuestSimObject guest, Semaphore semaphore)
    {
        this.guest = guest;
        this.semaphore = semaphore;
    }
}
