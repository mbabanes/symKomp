package sk.sim.gui.model;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import sk.sim.RestaurantSimObject;

import java.text.MessageFormat;

public class GuestsStatistics
{
    private DescriptiveStatistics queueWaitingTime = new DescriptiveStatistics();
    private DescriptiveStatistics orderWaitingTime = new DescriptiveStatistics();
    private DescriptiveStatistics visitTime = new DescriptiveStatistics();

    public GuestsStatistics()
    {
        RestaurantSimObject.servicedGuests.forEach(guest -> {
            queueWaitingTime.addValue(guest.getWaitingTimeInQueue().toMillis());
            orderWaitingTime.addValue(guest.getTimeOfWaitingForOrder().toMillis());
            visitTime.addValue(guest.getTimeOfVisit().toMillis());
        });
    }


    public String getMessage()
    {
        String message = prepareVisitTimeMessageStats();

        message += prepareQueueWaitingTimeMessage();
        message += prepareOrderWaitingTimeMessage();


        return message;
    }

    private String prepareVisitTimeMessageStats()
    {
        StringBuilder pattern = new StringBuilder();

        pattern.append("Średni czas pobytu gościa wynosi {0}\n")
                .append("Zsumowany czas pobytu: {1}\n")
                .append("Najdłuższy czas pobytu: {2}\n")
                .append("Najkrótszy czas pobytu: {3}\n")
                .append("Odchylenie standardowe od czasu pobytu: {4}\n")
                .append("Mediana czasu pobytu: {5}\n");

        return MessageFormat.format(pattern.toString(), visitTime.getMean(),
                visitTime.getSum(),
                visitTime.getMax(),
                visitTime.getMin(),
                visitTime.getStandardDeviation(),
                visitTime.getPercentile(50));
    }

    private String prepareQueueWaitingTimeMessage()
    {
        StringBuilder pattern = new StringBuilder();
        pattern.append("\nŚredni czas oczekiwania na zamówienie: {0}\n")
                .append("Najdłuższy czas oczelowamoe w kolejce: {1}\n")
                .append("Najkrótszy czas oczekiwnaia w kolejce: {2}\n")
                .append("Mediana czasu oczekiwania w kolejce: {3}\n")
                .append("Odchylenie standardowe od czasu oczekiwania w kolejce: {4}\n");

        return formatMessageOf(pattern.toString(), queueWaitingTime);
    }

    private String prepareOrderWaitingTimeMessage()
    {
        StringBuilder pattern = new StringBuilder();
        pattern.append("\nŚredni czas oczekiwania na zamówienie: {0}\n")
                .append("Najdłuższy czas oczekiwnaia na zamówienie: {1}\n")
                .append("Najkrótszy czas oczekiwnaia w zamówienie: {2}\n")
                .append("Mediana czasu oczekiwania na zamowienia: {3}\n")
                .append("Odchylenie standardowe od czasu oczekiwania na zamówienie: {4}\n");

        return formatMessageOf(pattern.toString(), orderWaitingTime);
    }

    private String formatMessageOf(String pattern, DescriptiveStatistics s)
    {
        return MessageFormat.format(pattern,
                s.getMean(),
                s.getMax(),
                s.getMin(),
                s.getPercentile(50),
                s.getStandardDeviation());
    }
}
