package sk.sim.object;

import deskit.SimObject;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class WaiterSimObject extends SimObject
{
    private static AtomicInteger ID = new AtomicInteger();

    private int id = ID.getAndIncrement();

    private AtomicInteger currentGuestNumber = new AtomicInteger();

    public boolean isFree()
    {
        if (currentGuestNumber.get() == 3)
            return false;
        return true;
    }


    public String debugMessage()
    {
        return "[Kelner " + id + "]:";
    }

}
