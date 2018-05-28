package sk.sim.gui.model.utill;

import javafx.util.StringConverter;

public class StringToIntegerConverter extends StringConverter<Number>
{
    @Override
    public String toString(Number number)
    {
        return Long.toString(number.longValue());
    }

    @Override
    public Number fromString(String string)
    {
        return Long.parseLong(string);
    }
}
