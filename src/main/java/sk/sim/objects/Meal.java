package sk.sim.objects;

import lombok.Getter;
import sk.sim.utill.Context;
import sk.sim.utill.Randomizer;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Meal
{
    private static final Randomizer random = Context.getRandomizerForMeal();

    private static AtomicInteger ID = new AtomicInteger(0);

    private int id;

    private AtomicInteger counter = new AtomicInteger(0);

    private long preparingTime;

    public Meal()
    {
        id = ID.getAndIncrement();
        preparingTime = random.nextInt(1400) + 1000;
    }

    public void increment()
    {
        counter.incrementAndGet();
    }

    @Override
    public String toString()
    {
        return "Meal{" +
                "id=" + id +
                ", counter=" + counter +
                '}';
    }

    public String debugMessage()
    {
        return "[Meal " + id + "]: ";
    }
}
