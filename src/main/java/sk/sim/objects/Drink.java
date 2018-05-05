package sk.sim.objects;

import lombok.Getter;
import sk.sim.utill.Context;
import sk.sim.utill.Randomizer;

@Getter
public class Drink
{
    private static final Randomizer random = Context.getRandomizerForDrink();

    private long preparingTime;

    public Drink()
    {
        preparingTime = random.nextInt(600);
    }
}
