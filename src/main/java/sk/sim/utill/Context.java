package sk.sim.utill;

public class Context
{
    private static Randomizer randomizer = new Randomizer()
    {
    };


    public static Randomizer getRandomizer()
    {
        return randomizer;
    }

    public static Randomizer getRandomizerForMeal()
    {
        return randomizer;
    }

    public static Randomizer getRandomizerForDrink()
    {
        return randomizer;
    }

    public static Randomizer getRandomizerForCook()
    {
        return randomizer;
    }
}
