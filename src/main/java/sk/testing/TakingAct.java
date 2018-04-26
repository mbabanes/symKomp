package sk.testing;

import deskit.SimActivity;

public class TakingAct extends SimActivity
{
    private WaitSimOb waiter;

    public TakingAct(WaitSimOb waiter)
    {
        this.waiter = waiter;
    }

    @Override
    public void action()
    {
        System.out.println("Kelner");
        while (Restaru.opened.get())
        {
            if(Restaru.expectantGuests.isEmpty())
            {
                waitDuration(100);
            }
            else
            {
                if ( waiter.takeGuest() )
                {
                    GuesObj guest = Restaru.expectantGuests.removeFirst();
                    guest.waiter = waiter;
                    callActivity(guest, new GuestOutingAct(guest));
                    waitDuration(200);
                }
                else
                {
                    waitDuration(300);
                }
            }
        }
    }
}
