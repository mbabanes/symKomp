package sk.sim.gui.visualisation.event;

public class GuestRestEvent extends Event
{
    public GuestRestEvent()
    {

    }

    @Override
    public void action()
    {
        guest.rest();
    }

    @Override
    public String toString()
    {
        return "GuestRestEvent{" +
                "guest=" + guest +
                '}';
    }
}
