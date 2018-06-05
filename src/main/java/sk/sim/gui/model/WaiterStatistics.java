package sk.sim.gui.model;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import sk.sim.RestaurantSimObject;

import java.text.MessageFormat;

public class WaiterStatistics
{
    private DescriptiveStatistics ds = new DescriptiveStatistics();

    public WaiterStatistics()
    {
        RestaurantSimObject.getWaiters().forEach(waiter ->{
            ds.addValue(waiter.getGuestNumber());
        });
    }


    public String getMessage()
    {
        StringBuilder pattern = new StringBuilder();
        pattern.append("\nŚrednia liczba obsłużonych gości na kelnera: {0}\n")
                .append("Mediana liczby obsłużonych gości na kelnera: {1}\n");

        String message = MessageFormat.format(
                pattern.toString(),
                ds.getMean(),
                ds.getPercentile(50));

        return message;
    }
}
