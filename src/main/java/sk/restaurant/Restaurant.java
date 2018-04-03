package sk.restaurant;

import sk.entity.Guest;
import sk.util.Observer;
import sk.entity.Table;
import sk.entity.Waiter;
import sk.restaurant.expection.NoSuchTableException;

import java.util.*;

public class Restaurant implements Observer<Waiter>
{
    private static final Random random = new Random();

    private Deque<Guest> expectantGuests = new LinkedList<>();

    private List<Waiter> waiters = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();


    @Override
    public void update(Waiter waiter)
    {
        Guest guest = null;
        try
        {
            guest = expectantGuests.removeFirst();
            invite(guest, waiter);
        }
        catch (NoSuchTableException e)
        {
            expectantGuests.addFirst(guest);
        }
        catch(NoSuchElementException e){System.out.println("Nie ma więcej gości");}
    }

    private void prepareTablesWaitersAndGuests()
    {
        Table table1 = new Table();
        Table table2 = new Table();
        Table table3 = new Table();

        Table table4 = new Table();
        Table table5 = new Table();
        Table table6 = new Table();


        tables.add(table1);
        tables.add(table2);
        tables.add(table3);
        tables.add(table4);
        tables.add(table5);
        tables.add(table6);

        Waiter waiter1 = new Waiter(this);
        waiter1.addTable(table1);
        waiter1.addTable(table2);
        waiter1.addTable(table3);


        Waiter waiter2 = new Waiter(this);
        waiter2.addTable(table4);
        waiter2.addTable(table5);
        waiter2.addTable(table6);

        waiters.add(waiter1);
        waiters.add(waiter2);

        for (int i = 0; i < 10; i++)
        {
            Guest guest = new Guest();
            expectantGuests.addLast(guest);
        }
    }

    public void aasd() throws InterruptedException
    {
        prepareTablesWaitersAndGuests();

        while (!expectantGuests.isEmpty())
        {
            System.out.println("\nRestauracja pracuje");
            Optional<Waiter> waiterOptional = waiters.stream()
                    .filter(Waiter::isFree)
                    .findFirst();

            if (waiterOptional.isPresent())
            {
                Guest guest = expectantGuests.removeFirst();
                Waiter waiter = waiterOptional.get();
                try {
                    invite(guest, waiter);
                }catch (NoSuchTableException e)
                {
                    expectantGuests.addFirst(guest);
                    System.out.println("Brak miejsc");

                    waitForFreeTable(guest);

                }
            }
            else
            {
                Guest guest = expectantGuests.getFirst();
                waitForFreeTable(guest);
            }

        }

    }

    private void invite(Guest guest, Waiter waiter) throws NoSuchTableException
    {
        waiter.invite(guest);
        System.out.println("Kelenr: " + waiter + " obsluguje gościa: " + guest);
        System.out.println();
    }

    private void waitForFreeTable(Guest guest) throws InterruptedException
    {
        long  waitTime = random.nextInt(800);
        System.out.println("Gosc " + guest + " czeka na wolny stolik: " + waitTime + "ms\n");

        Thread.sleep(waitTime);
        makeTableFree();

    }

    private void makeTableFree()
    {
        int tableNumber = random.nextInt(tables.size());
        tables.get(tableNumber).setFree(true);
    }

}
