package sk.entity;

import lombok.Getter;

@Getter
public class Guest
{
    private static int ID = 0;

    private boolean place = false;
    private int id;


    public Guest()
    {
        this.id = ++ID;
    }

    public boolean hasPlace()
    {
        return place;
    }

    public void setPlace(boolean place)
    {
        this.place = place;
    }

    public void give(Order order)
    {

    }

    @Override
    public String toString()
    {
        return "Guest{" +
                "id=" + id +
                '}';
    }
}
