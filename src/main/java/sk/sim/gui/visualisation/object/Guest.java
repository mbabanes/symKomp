package sk.sim.gui.visualisation.object;

import javafx.scene.control.Label;
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

    public void out()
    {
        table.setFree(true);
        circle.setVisible(false);
        statusLabel.setVisible(false);
    }

    public void sitDown()
    {
        statusLabel.setText("Siada");

        Queue.getOut(this);
        Optional<Table> tableOptional = guest.getWaiterSimObject().getTables().stream().filter(Table::isFree).findFirst();
        table = tableOptional.get();
        table.setFree(false);
        Circle circle = table.getCircle();

        double x = circle.getLayoutX() - 20;
        double y = circle.getLayoutY() - 10;
        this.circle.relocate(x, y);
        statusLabel.relocate(x - 50, y - 30);
    }

    public void stayInQueue()
    {
        Queue.come(this);
    }

    public int getId()
    {
        return guest.getId();
    }

    public void placeOrder()
    {
        statusLabel.setText("Zamawia  ");
    }

    public void eat()
    {
        statusLabel.setText("Je");
    }

    public void rest()
    {
        statusLabel.setText("Odpo  ");
    }

    @Override
    public String toString()
    {
        return "Guest{" +
                "guest=" + guest.getId() +
                '}';
    }
}
