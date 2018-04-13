package sk.main;

import sk.entity.Order;
import sk.restaurant.Restaurant;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
//        Restaurant restaurant = new Restaurant();
//
//
//            restaurant.verySimpleSimulation();

        Order order = new Order();

        order.setId(1);

        System.out.println(order.getId());

    }
}
