package sk.sim.objects;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    private List<Meal> meals = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();

    private long preparingTime;

    public void addMeal(Meal meal)
    {
        meals.add(meal);

        preparingTime += meal.getPreparingTime();

    }

    public void addDrink(Drink drink)
    {
        drinks.add(drink);
        preparingTime += drink.getPreparingTime();
    }

    public long getPreparingTime()
    {
        return preparingTime;
    }

    public int getMealsNumber()
    {
        return meals.size();
    }

    public int getDrinksNumber()
    {
        return drinks.size();
    }
}
