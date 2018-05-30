package sk.sim.gui.model;

import sk.sim.RestaurantSimObject;
import sk.sim.activities.cook.OrderQueue;
import sk.sim.objects.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SimulationBasicStats
{
    public String waiterStatistics()
    {
        StringBuilder stringBuilder = prepareWaitersStatistic();

        return stringBuilder.toString();
    }

    public String cooksStatistics()
    {
        StringBuilder stringBuilder = prepareCookerStatistic();

        return stringBuilder.toString();
    }

    public String mealsAndDrinksStatistics()
    {
        StringBuilder stringBuilder = new StringBuilder();

        prepareMealsStatsAndSaveIn(stringBuilder);
        prepareDrinksStatsAndSaveIn(stringBuilder);


        return stringBuilder.toString();
    }

    public String guestsStats()
    {
        return prepareGuestsStatisticsMessage();
    }


    private StringBuilder prepareWaitersStatistic()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Set<WaiterSimObject> waiters = RestaurantSimObject.getWaiters();
        waiters.forEach(waiter ->
                {
                    stringBuilder
                            .append(waiter.debugMessage())
                            .append(" obsłużył:")
                            .append(waiter.getGuestNumber())
                            .append("\n");
                }
        );
        return stringBuilder;
    }

    private void prepareMealsStatsAndSaveIn(StringBuilder stringBuilder)
    {
        stringBuilder.append("Dania:\n");
        List<Meal> meals = Menu.getMeals();
        meals.forEach(meal -> {
            stringBuilder
                    .append("[Meal ")
                    .append(meal.getId())
                    .append("] zamowiono: ")
                    .append(meal.getCounter())
                    .append('\n');
        });
    }

    private void prepareDrinksStatsAndSaveIn(StringBuilder stringBuilder)
    {
        stringBuilder.append("Napoje:\n");
        List<Drink> drinks = Menu.getDrinks();
        drinks.forEach(drink ->{
            stringBuilder
                    .append("[Drink ")
                    .append(drink.getId())
                    .append("] zamowiono: ")
                    .append(drink.getCounter())
                    .append('\n');
        });
    }

    private String prepareGuestsStatisticsMessage()
    {
        StringBuilder stringBuilder = new StringBuilder();

        List<GuestSimObject> guests = sortGuestsByIdAsc();

        guests.forEach(guest ->
        {
            stringBuilder
                    .append(guest.debugMessage())
                    .append("Czas wizyty: ")
                    .append(guest.getTimeOfVisit())
                    .append(" ms")
                    .append(" | Czas oczekiwania na zamowienie: ")
                    .append(guest.getTimeOfWaitingForOrder())
                    .append(" ms")
                    .append(" | Czas w kolejce: ")
                    .append(guest.getWaitingTimeInQueue())
                    .append(" ms\n");
        });

        return stringBuilder.toString();
    }

    private StringBuilder prepareCookerStatistic()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Set<CookSimObject> cooks = RestaurantSimObject.getCooks();

        cooks.forEach(cook ->{
            stringBuilder
                    .append(cook.debugMessage())
                    .append("Obsłużył ")
                    .append(cook.getPreparedOrdersNumber().get())
                    .append('\n');
        });

        return stringBuilder;
    }

    private List<GuestSimObject> sortGuestsByIdAsc()
    {
        return RestaurantSimObject.servicedGuests
                .stream()
                .sorted(Comparator.comparingInt(GuestSimObject::getId))
                .collect(Collectors.toList());
    }


    public String ordersStats()
    {
        StringBuilder stringBuilder = new StringBuilder();
        List<OrderSimObject> orders = OrderQueue.allOrders.stream().sorted(Comparator.comparingInt(OrderSimObject::getId)).collect(Collectors.toList());

        orders.forEach(order ->{
            stringBuilder.append(order.debugMessage()).append(order.done.get()).append('\n');
        });

        return stringBuilder.toString();
    }
}
