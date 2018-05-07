package sk.sim.gui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;



public class RestaurantFx
{
    private IntegerProperty guestNumberIntegerProperty = new SimpleIntegerProperty(20);
    private IntegerProperty waitersNumberIntegerProperty = new SimpleIntegerProperty(3);
    private IntegerProperty mealsNumberIntegerProperty = new SimpleIntegerProperty(10);
    private IntegerProperty drinksNumberIntegerProperty = new SimpleIntegerProperty(5);


    public int getGuestNumber()
    {
        return guestNumberIntegerProperty.get();
    }

    public IntegerProperty guestNumberIntegerProperty()
    {
        return guestNumberIntegerProperty;
    }

    public int getWaitersNumber()
    {
        return waitersNumberIntegerProperty.get();
    }

    public IntegerProperty waitersNumberIntegerProperty()
    {
        return waitersNumberIntegerProperty;
    }

    public int getMealsNumber()
    {
        return mealsNumberIntegerProperty.get();
    }

    public IntegerProperty mealsNumberIntegerProperty()
    {
        return mealsNumberIntegerProperty;
    }

    public int getDrinksNumber()
    {
        return drinksNumberIntegerProperty.get();
    }

    public IntegerProperty drinksNumberIntegerProperty()
    {
        return drinksNumberIntegerProperty;
    }

    @Override
    public String toString()
    {
        return "RestaurantFx{" +
                "guestNumberIntegerProperty=" + guestNumberIntegerProperty +
                ", waitersNumberIntegerProperty=" + waitersNumberIntegerProperty +
                ", mealsNumberIntegerProperty=" + mealsNumberIntegerProperty +
                ", drinksNumberIntegerProperty=" + drinksNumberIntegerProperty +
                '}';
    }
}