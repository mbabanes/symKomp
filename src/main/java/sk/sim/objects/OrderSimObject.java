package sk.sim.objects;

import deskit.SimObject;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class OrderSimObject extends SimObject
{
    private static AtomicInteger ID = new AtomicInteger(0);

    private int id;

    private List<Meal> meals = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();

    private GuestSimObject guestSimObject;

    private long preparingTime;

    private Instant startTime;
    private Instant receiptTime;


    public OrderSimObject()
    {
        this.id = ID.getAndIncrement();
    }

    public OrderSimObject(GuestSimObject guestSimObject)
    {
        this();
        this.guestSimObject = guestSimObject;
    }

    public void addMeal(Meal meal)
    {
        meals.add(meal);

        preparingTime += meal.getPreparingTime();
        meal.increment();

    }

    public void addDrink(Drink drink)
    {
        drinks.add(drink);
        preparingTime += drink.getPreparingTime();
        drink.increment();
    }

    public void setStartTime(Instant startTime)
    {
        this.startTime = startTime;
    }

    public void setReceiptTime(Instant receiptTime)
    {
        this.receiptTime = receiptTime;
    }

    public int getMealsNumber()
    {
        return meals.size();
    }

    public int getDrinksNumber()
    {
        return drinks.size();
    }

    public void setGuest(GuestSimObject guest)
    {
        this.guestSimObject = guest;
    }

    public String debugMessage()
    {
        return "[Zamowienie " + id + "]: ";
    }
}
