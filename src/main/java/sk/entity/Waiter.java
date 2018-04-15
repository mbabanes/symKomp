package sk.entity;

import deskit.SimObject;
import sk.restaurant.expection.NoSuchTableException;
import sk.util.Observer;
import sk.util.Observable;
import java.util.*;

public class Waiter
        implements Observable, Observer<Table>
{
    private final static int MAX_GUESTS_NUMBER = 3;

    private static int ID = 0;

    private int id;

    private Map<Integer, Guest> guests = new HashMap<>();

    private List<Table> tables = new ArrayList<>();

    private Observer<Waiter> restaurant;

    private int guestsNumber = 0;

    private boolean free = true;


    public Waiter()
    {
    }

    public Waiter(Observer<Waiter> restaurant)
    {
        addObserver(restaurant);
        this.id = ++ID;
    }

    @Override
    public void addObserver(Observer o)
    {
        this.restaurant = (Observer<Waiter>) o;
    }

    public boolean isFree()
    {
        return free;
    }

    public void setFree(boolean free)
    {
        this.free = free;
        if(free)
            report();
    }

    @Override
    public void report()
    {
        restaurant.update(this);
    }


    @Override
    public void update(Table table)
    {
        guests.remove(table.getNumber());
        guestsNumber--;
        setFree(true);
    }

    public void addTable(Table table)
    {
        tables.add(table);
        table.addObserver(this);
    }

    @Override
    public String toString()
    {
        return "Waiter{" +
                "id=" + id +
                '}';
    }

    public void invite(Guest guest) throws NoSuchTableException
    {
        if (isTooBusy())
            setFree(false);
        else
        {
            Table table = findFreeTable();
            invite(table, guest);
        }
    }

    public void giveOrder(Order order)
    {
        guests.get(order.getGuest().getTableNumber())
              .give(order);
    }

    private boolean isTooBusy()
    {
        return guestsNumber == MAX_GUESTS_NUMBER;
    }

    private Table findFreeTable() throws NoSuchTableException
    {
        Optional<Table> firstFreeTableOptional =
                tables.stream()
                        .filter(Table::isFree)
                        .findFirst();
        return firstFreeTableOptional.orElseThrow(NoSuchTableException::new);
    }

    private void invite(Table table, Guest guest)
    {
        guests.put(table.getNumber(), guest);
        guest.setTableNumber(table.getNumber());
        table.setFree(false);
        guestsNumber++;

        if( isTooBusy() )
            setFree(false);

    }
}
