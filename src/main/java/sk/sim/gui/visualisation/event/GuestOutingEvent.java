package sk.sim.gui.visualisation.event;

public class GuestOutingEvent extends Event
{
    public GuestOutingEvent()
    {
    }

    @Override
    public void action()
    {
        guest.out();
    }

    @Override
    public String toString()
    {
        return "GuestOutingEvent{" +
                "guest=" + guest +
                '}';
    }
}
