package sk.sim.gui.visualisation.event;

import sk.sim.gui.visualisation.object.Guest;

public class NewGuestComingEvent extends Event
{
    public NewGuestComingEvent(Guest guest)
    {
        super(guest);
    }

    @Override
    public void action()
    {
        guest.stayInQueue();
    }

    @Override
    public String toString()
    {
        return "NewGuestComingEvent{" +
                "guest=" + guest +
                '}';
    }
}
