package sk.sim.gui.visualisation.object;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import lombok.Getter;
import lombok.Setter;

import sk.sim.gui.visualisation.Queue;
import sk.sim.objects.GuestSimObject;

import java.util.Optional;

@Setter
@Getter
public class Guest
{
    private GuestSimObject guest;

    private Circle circle;
    private Label statusLabel;

    private Table table;

    public Guest(GuestSimObject guest)
    {
        this.guest = guest;
    }

    public void stayInQueue()
    {
        Queue.come(this);
    }

    public void sitDown()
    {
        statusLabel.setText("Siada");

        Queue.getOut(this);
        table = findFreeTable();
        table.sit();
        moveCircleToTable();

    }

    public void placeOrder()
    {
        statusLabel.setText("Zamawia  ");
    }

    public void waitForOrder()
    {
        statusLabel.setText("Czeka");
    }

    public void eat()
    {
        statusLabel.setText("Je");
    }

    public void rest()
    {
        statusLabel.setText("Odpo");
    }

    public void out()
    {
        table.makeFree();
        removeCircleAndLabelFromCanvas();
    }

    public int getId()
    {
        return guest.getId();
    }

    @Override
    public String toString()
    {
        return "Guest{" +
                "guest=" + guest.getId() +
                '}';
    }

    private void removeCircleAndLabelFromCanvas()
    {
        Parent parent = circle.getParent();
        ((Pane) parent ).getChildren().remove(circle);
        ((Pane) parent ).getChildren().remove(statusLabel);
    }

    private Table findFreeTable()
    {
        Optional<Table> tableOptional = guest.getWaiterSimObject()
                .getTables()
                .stream()
                .filter(Table::isFree)
                .findFirst();
        return tableOptional.get();
    }

    private void moveCircleToTable()
    {
        Circle circle = table.getCircle();

        double x = circle.getLayoutX() - 20;
        double y = circle.getLayoutY() - 10;
        this.circle.relocate(x, y);


        statusLabel.relocate(x, y + 30);
    }
}
