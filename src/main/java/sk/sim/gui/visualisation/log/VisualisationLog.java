package sk.sim.gui.visualisation.log;

import sk.sim.gui.visualisation.event.Event;
import sk.sim.gui.visualisation.event.NewGuestComingEvent;
import sk.sim.gui.visualisation.object.Guest;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class VisualisationLog
{
    public static Deque<Event> events = new LinkedBlockingDeque<>();

    private static Map<Integer, Guest> guests = new ConcurrentHashMap<>();

    public static void log(int guestId, Event event)
    {
        Guest guest = guests.get(guestId);
        event.setGuest(guest);
        events.addLast(event);
    }

    public static void addNewGuest(Guest guest)
    {
        guests.put(guest.getId(), guest);
        events.addLast(new NewGuestComingEvent(guest));
    }
}
