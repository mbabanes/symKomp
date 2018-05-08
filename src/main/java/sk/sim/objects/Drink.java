package sk.sim.objects;

import lombok.Getter;
import sk.sim.utill.Context;
import sk.sim.utill.Randomizer;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Drink
{
    private static final Randomizer random = Context.getRandomizerForDrink();

    private static AtomicInteger ID = new AtomicInteger(0);
    private int id;
    private long preparingTime;

    private AtomicInteger counter = new AtomicInteger(0);

    public Drink()
    {
        id = ID.getAndIncrement();
        preparingTime = random.nextInt(600);
    }

    public void increment()
    {
        counter.incrementAndGet();
    }
}
