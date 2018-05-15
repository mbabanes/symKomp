package sk.sim.gui.visualisation.event;

public class GuestEatingEvent extends Event
{
    public GuestEatingEvent()
    {
    }

    @Override
    public void action()
    {
        guest.eat();
    }

    @Override
    public String toString()
    {
        return "GuestEatingEvent{" +
                "guest=" + guest +
                '}';
    }
}
