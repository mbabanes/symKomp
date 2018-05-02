package sk.sim.utill;

public class Autowired
{
    private static Randomizer randomizer = new Randomizer()
    {
    };


    public static Randomizer getRandomizer()
    {
        return randomizer;
    }
}
