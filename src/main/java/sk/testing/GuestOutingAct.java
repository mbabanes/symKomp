package sk.testing;

import deskit.SimActivity;

import java.util.Random;

public class GuestOutingAct extends SimActivity
{
    private static Random random = new Random();

    private GuesObj guest;

    public GuestOutingAct(GuesObj guest)
    {
        this.guest = guest;
    }

    @Override
    public void action()
    {
        waitDuration(random.nextInt(600) + 500);
        guest.out();
    }
}
