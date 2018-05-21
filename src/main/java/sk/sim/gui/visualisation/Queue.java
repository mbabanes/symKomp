package sk.sim.gui.visualisation;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sk.sim.gui.visualisation.object.Guest;

import java.util.Deque;
import java.util.LinkedList;

public class Queue
{
    private static Deque<Guest> queue = new LinkedList<>();


    public static void come(Guest guest)
    {
        Circle circle = new Circle(10, Color.BLUE);
        Label label = new Label();

        draw(circle, label);

        queue.addLast(guest);
        guest.setCircle(circle);
        guest.setStatusLabel(label);

        Visualisation.canvas.getChildren().add(circle);
        Visualisation.canvas.getChildren().add(label);
    }


    public static void getOut(Guest guest)
    {
        queue.remove(guest);

        if (!queue.isEmpty())
        {
            queue.forEach(Queue::moveUp);
        }
    }

    private static void draw(Circle circle, Label label)
    {
        if (queue.isEmpty())
        {
            circle.relocate(20, 150);
            label.relocate(30, 150);

        } else
        {
            Guest previousGuest = queue.getLast();
            circle.relocate(20, previousGuest.getCircle().getLayoutY() + 15);
            label.relocate(30, previousGuest.getStatusLabel().getLayoutY() + 15);
        }
    }

    private static void moveUp(Guest guestInQueue)
    {
        Circle circle = guestInQueue.getCircle();
        circle.relocate(20, circle.getLayoutY() - 35);

        Label label = guestInQueue.getStatusLabel();
        label.relocate(30, label.getLayoutY() - 35);
    }

}
