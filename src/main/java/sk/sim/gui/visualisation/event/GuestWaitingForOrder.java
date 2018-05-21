package sk.sim.gui.visualisation.event;

public class GuestWaitingForOrder extends Event
{
    @Override
    public void action()
    {
        this.guest.waitForOrder();
    }
}
