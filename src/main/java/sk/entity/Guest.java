package sk.entity;

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

    @Override
    public String toString()
    {
        return "Guest{" +
                "id=" + id +
                '}';
    }
}
