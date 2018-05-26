package sk.sim.utill;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Supplier;

public class Logger
{
    private static Deque<String> log = new LinkedList<>();

    public static void log(String message)
    {
//        logger.appendText(message + '\n');
        log.addLast(message + '\n');
    }

    public static void log(Supplier<String> messageSup)
    {
        log(messageSup.get());
    }

    public static void consoleLog(Supplier<String> messageSup)
    {
//        System.out.println(messageSup.get());
    }

    public static Deque<String> getLog()
    {
        return log;
    }
}
