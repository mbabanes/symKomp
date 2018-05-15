package sk.sim.gui.visualisation.event;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class Events
{
    private Deque<Event> events = new LinkedBlockingDeque<>();

    public Events(Event newGuestComingEvent)
    {
        events.addLast(newGuestComingEvent);
    }


    public void addEvent(Event event)
    {
        events.addLast(event);
    }

    public Event getCurrentEvent()
    {
        return events.removeFirst();
    }
}
