package sk.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order
{
    private int id;
    private Guest guest;
    private int tableNumber;
    private Waiter waiter;
    private List<Meal> meals = new ArrayList<>();
    private int time;


    public Order()
    {
    }

    public Order(Guest guest)
    {
        this.guest = guest;
    }


}
