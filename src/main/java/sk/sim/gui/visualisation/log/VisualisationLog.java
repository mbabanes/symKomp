package sk.sim.gui.visualisation.log;

import sk.sim.gui.visualisation.event.Event;
import sk.sim.gui.visualisation.event.Events;
import sk.sim.gui.visualisation.event.NewGuestComingEvent;
import sk.sim.gui.visualisation.object.Guest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class VisualisationLog
{
    private static AtomicInteger order = new AtomicInteger(0);

    public static Map<Guest, Events> events = new ConcurrentHashMap<>();
    public static Map<Integer, Guest> eventsOrder = new ConcurrentHashMap<>();
    private static Map<Integer, Guest> guests = new ConcurrentHashMap<>();

    public static void log(int guestId, Event event)
    {
        Guest guest = guests.get(guestId);

        event.setGuest(guest);

        eventsOrder.put(order.getAndIncrement(), guest);

        Events guestEvents = events.get(guest);
        guestEvents.addEvent(event);
    }

    public static void addNewGuest(Guest guest)
    {
        guests.put(guest.getId(), guest);
        eventsOrder.put(order.getAndIncrement(), guest);

        events.put(guest, new Events(new NewGuestComingEvent(guest)));
    }
}
