package sk.sim.gui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;



public class RestaurantFx
{
    private IntegerProperty guestNumberIntegerProperty = new SimpleIntegerProperty(400);
    private IntegerProperty waitersNumberIntegerProperty = new SimpleIntegerProperty(3);
    private IntegerProperty mealsNumberIntegerProperty = new SimpleIntegerProperty(25);
    private IntegerProperty drinksNumberIntegerProperty = new SimpleIntegerProperty(15);
    private IntegerProperty cookNumberIntegerProperty = new SimpleIntegerProperty(3);


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

    public int getCookNumber()
    {
        return cookNumberIntegerProperty.get();
    }

    public IntegerProperty cookNumberIntegerProperty()
    {
        return cookNumberIntegerProperty;
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
