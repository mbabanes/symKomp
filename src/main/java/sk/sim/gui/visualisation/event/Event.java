package sk.sim.gui.visualisation.event;

import sk.sim.gui.visualisation.object.Guest;

abstract public class Event
{
    protected Guest guest;

    public Event()
    {
    }



    public Event(Guest guest)
    {
        this.guest = guest;
    }

    public abstract void action();

    public void setGuest(Guest guest)
    {
        this.guest = guest;
    }
}
