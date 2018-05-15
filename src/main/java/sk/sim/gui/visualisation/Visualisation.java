package sk.sim.gui.visualisation;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sk.sim.RestaurantSimObject;
import sk.sim.gui.visualisation.object.Table;
import sk.sim.objects.WaiterSimObject;

public class Visualisation
{
    private Pane canvas;

    public Visualisation(Pane canvas)
    {
        this.canvas = canvas;
    }

    public void initTables()
    {
        drawTables();
    }

    private void drawTables()
    {
        double startX = 200;
        double startY = 120;

        double x = startX;
        double y = startY;

        int i = 1;

        for (WaiterSimObject waiter : RestaurantSimObject.getWaiters())
        {
            for (Table table : waiter.getTables())
            {
                Circle circle = new Circle(20, Color.GREEN);
                circle.relocate(x, y);

                table.setCircle(circle);
                canvas.getChildren().add(circle);
                x += 80;
                if (i % 6 == 0)
                {
                    x = startX;
                    y += 80;
                }

                i++;
            }
        }
    }


}
