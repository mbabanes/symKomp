package sk.sim.utill;

import java.util.Random;

public interface Randomizer
{
    static Random random = new Random();

    public default int nextInt(int bound)
    {
        return random.nextInt(bound);
    }
}
