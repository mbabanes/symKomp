package sk.sim.gui.visualisation.event;

import sk.sim.gui.visualisation.object.Guest;

public class GuestSitingDownEvent extends Event
{
    public GuestSitingDownEvent()
    {
    }

    public GuestSitingDownEvent(Guest guest)
    {
        super(guest);
    }

    @Override
    public void action()
    {
        guest.sitDown();
    }


    @Override
    public String toString()
    {
        return "GuestSitingDownEvent{" +
                "guest=" + guest +
                '}';
    }
}
