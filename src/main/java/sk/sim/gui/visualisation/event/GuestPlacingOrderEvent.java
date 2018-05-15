package sk.sim.gui.visualisation.event;

public class GuestPlacingOrderEvent extends Event
{
    public GuestPlacingOrderEvent()
    {
    }

    @Override
    public void action()
    {
        guest.placeOrder();
    }

    @Override
    public String toString()
    {
        return "GuestPlacingOrderEvent{" +
                "guest=" + guest +
                '}';
    }
}
