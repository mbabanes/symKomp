package sk.sim.object;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Guest
{
    private static AtomicInteger ID = new AtomicInteger();

    private int id = ID.getAndIncrement();



    @Override
    public String toString()
    {
        return "Guest{" +
                "id=" + id +
                '}';
    }
}
