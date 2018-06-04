package sk.sim.gui.model;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import sk.sim.RestaurantSimObject;

import java.text.MessageFormat;

public class CookStatistics
{
    private DescriptiveStatistics ds = new DescriptiveStatistics();

    public CookStatistics()
    {
        RestaurantSimObject.getCooks().forEach(cook ->{
            ds.addValue(cook.getPreparedOrdersNumber().get());
        });
    }

    public String getMessage()
    {
        StringBuilder pattern = new StringBuilder();
        pattern.append("\nŚrednia liczba zrealizowanych zamówień na kucharza: {0}\n")
                .append("Mediana liczby zrealizowanych zamówień na kucharza: {1}\n");

        String message = MessageFormat.format(
                pattern.toString(),
                ds.getMean(),
                ds.getPercentile(0.5));

        return message;
    }
}
