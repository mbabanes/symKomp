package sk.entity;

import deskit.SimObject;
import sk.util.Observable;
import sk.util.Observer;

import java.util.ArrayList;
import java.util.List;

public class Table
        implements Observable
{
    protected static int ID = 0;

    private int number;
    private boolean free = true;

    private List<Observer<Table>> patrons = new ArrayList<>();



    public Table()
    {
        number = ++ID;
    }

    public boolean isFree()
    {
        return free;
    }

    public void setFree(boolean free)
    {
        this.free = free;
        if(free)
        {
            report();
        }
    }

    @Override
    public void addObserver(Observer o)
    {
        patrons.add( (Observer<Table>) o);
    }

    @Override
    public void report()
    {
        System.out.println("Zwolnił się stolik nr: " + number);
        patrons.forEach(patron -> patron.update(this));
    }

    public int getNumber()
    {
        return number;
    }
}
