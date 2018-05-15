package sk.sim.gui.visualisation.object;

import sk.sim.gui.visualisation.utill.Location;
import sk.sim.objects.GuestSimObject;

public class Guest
{
    private GuestSimObject guest;

    private Location currentLocation;


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

    @Override
    public String toString()
    {
        return "Guest{" +
                "guest=" + guest.getId() +
                '}';
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
}
