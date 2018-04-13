package sk.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Guest
{
    private static int ID = 0;

    private boolean place = false;
    private int id;
    private int tableNumber;


    public Guest()
    {
        this.id = ++ID;
    }

    public boolean hasPlace()
    {
        return place;
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
