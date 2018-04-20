package sk.service;

import deskit.SimActivity;
import sk.entity.Guest;
import sk.entity.Waiter;
import sk.restaurant.Restaurant;
import sk.restaurant.expection.NoSuchTableException;

import java.util.Optional;
import java.util.Random;

public class WaiterActivity extends SimActivity
{

    private static Random random = new Random();


    //    private
    @Override
    public void action()
    {
        while (Restaurant.expectantGuests.isEmpty())
        {
            Optional<Waiter> waiterOptional = Restaurant.waiters.stream()
                    .filter(Waiter::isFree)
                    .findFirst();

            if (waiterOptional.isPresent())
            {
                Guest guest = Restaurant.expectantGuests.removeFirst();
                Waiter waiter = waiterOptional.get();

                try
                {
                    invite(guest, waiter);
                }
                catch (NoSuchTableException e)
                {
                    Restaurant.expectantGuests.addFirst(guest);
                }
            }else
            {
                try
                {
                    waitForFreeTable(Restaurant.expectantGuests.getFirst());
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
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

    public void makeTableFree()
    {
        int tableNumber = random.nextInt(Restaurant.tables.size());
        Restaurant.tables.get(tableNumber).setFree(true);
    }
}
