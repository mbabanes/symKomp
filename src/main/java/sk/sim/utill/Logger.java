package sk.sim.utill;

import java.util.function.Supplier;

public class Logger
{
    private static StringBuilder log = new StringBuilder();

    public static void log(String message)
    {
        log.append(message).append('\n');
    }

    public static void log(Supplier<String> messageSup)
    {
        log(messageSup.get());
    }

    public static void consoleLog(Supplier<String> messageSup)
    {
//        System.out.println(messageSup.get());
    }


    public static String getLog()
    {
        return log.toString();
    }
}
