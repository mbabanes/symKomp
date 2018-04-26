package sk.testing;

import deskit.SimActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class NewGuestAct extends SimActivity
{
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void action()
    {

//        waitDuration(10000);
        while (true)
        {
            addNewGuest();
            if (Restaru.expectantGuests.size() > 5)
            {
                System.out.println("Nowi goscie stop");
                waitDuration(578);

            }

            if (counter.getAndIncrement() == 25)
            {
                System.out.println("Nowi gosci juz koncza");
                while(Restaru.expectantGuests.size() > 0)
                {
                    waitDuration(200);
                }

                Restaru.opened.set(false);
                break;
            }
        }
    }

    private void addNewGuest()
    {
        Restaru.expectantGuests.addLast(new GuesObj());
    }
}
