package sk.sim.gui.visualisation.object;

import javafx.scene.shape.Circle;
import sk.sim.objects.GuestSimObject;

public class Guest
{
    private GuestSimObject guest;

   private Circle circle;


    public Guest(GuestSimObject guest)
    {
        this.guest = guest;
    }

    public void out()
    {

    }

    public void sitDown()
    {

    }

    public void stayInQueue()
    {

    }

    public int getId()
    {
        return guest.getId();
    }

    public void placeOrder()
    {

    }

    public void eat()
    {
    }

    public void rest()
    {

    }

    @Override
    public String toString()
    {
        return "Guest{" +
                "guest=" + guest.getId() +
                '}';
    }
}
