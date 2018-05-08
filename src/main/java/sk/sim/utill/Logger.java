package sk.sim.utill;

import javafx.scene.control.TextArea;

public class Logger
{
    public static TextArea loggerr;

    public static void log(String message)
    {
        loggerr.appendText(message + '\n');
    }
}
