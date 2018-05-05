package sk.sim.objects;

import java.util.ArrayList;
import java.util.List;

public class Menu
{
    private static List<Drink> drinks = new ArrayList<>();
    private static List<Meal> meals = new ArrayList<>();


    public static Meal getMeal(int id)
    {
        return meals.get(id);
    }

    public static Drink getDrink(int id)
    {
        return drinks.get(id);
    }

    public static void addDrink(Drink drink)
    {
        drinks.add(drink);
    }

    public static void addMeal(Meal meal)
    {
        meals.add(meal);
    }
}
