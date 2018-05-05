package sk.sim.objects;

import lombok.Getter;
import sk.sim.utill.Context;
import sk.sim.utill.Randomizer;

@Getter
public class Meal
{
    private static final Randomizer random = Context.getRandomizerForMeal();

    private long preparingTime;

    public Meal()
    {
        preparingTime = random.nextInt(1400);
    }


}
