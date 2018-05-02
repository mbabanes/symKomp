package sk.sim.activities.guests.visit;

import deskit.SimActivity;
import deskit.synchronizers.Semaphore;
import sk.sim.objects.GuestSimObject;
import sk.sim.utill.Autowired;
import sk.sim.utill.Randomizer;

import java.util.concurrent.CountDownLatch;

abstract public class GuestVisitActivity extends SimActivity
{
    protected static Randomizer random = Autowired.getRandomizer();

    protected GuestSimObject guest;
    protected CountDownLatch countDownLatch;

    protected Semaphore semaphore;

    public GuestVisitActivity(GuestSimObject guest, CountDownLatch countDownLatch, Semaphore semaphore)
    {
        this.guest = guest;
        this.countDownLatch = countDownLatch;
        this.semaphore = semaphore;
    }

    protected GuestVisitActivity(GuestSimObject guest, CountDownLatch countDownLatch)
    {
        this.guest = guest;
        this.countDownLatch = countDownLatch;
    }


}
