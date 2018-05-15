package sk.sim.objects;

import deskit.SimObject;
import lombok.Getter;
import lombok.Setter;
import sk.sim.gui.visualisation.object.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class WaiterSimObject extends SimObject
{
    private static final int MAX_GUEST = 3;

    private static AtomicInteger ID = new AtomicInteger();

    private int id = ID.getAndIncrement();

    private AtomicInteger guestsNumber = new AtomicInteger(0);

    private AtomicInteger currentGuestNumber = new AtomicInteger();


    private List<Table> tables = new ArrayList<>();


    public WaiterSimObject()
    {
        for (int i = 0; i < MAX_GUEST; i++)
        {
            tables.add(new Table());
        }
    }

    public boolean isFree()
    {
        if (currentGuestNumber.get() == MAX_GUEST)
            return false;
        return true;
    }


    public void takeGuest()
    {
        currentGuestNumber.getAndIncrement();
        guestsNumber.incrementAndGet();
    }


    public String debugMessage()
    {
        return "[Kelner " + id + "]:";
    }

    public int getGuestNumber()
    {
        return guestsNumber.get();
    }

}
