package sk.sim.gui.visualisation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sk.sim.RestaurantSimObject;
import sk.sim.gui.visualisation.event.Event;
import sk.sim.gui.visualisation.log.VisualisationLog;
import sk.sim.gui.visualisation.object.Table;
import sk.sim.objects.WaiterSimObject;

public class Visualisation
{
    public static Pane canvas;
    public static Label label;

    private Timeline timeline;

    public Visualisation()
    {
        initTables();
        timeline = new Timeline();
        prepareFramesForVisualisation(timeline.getKeyFrames());
    }

    public void runVisualisation()
    {
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void initTables()
    {
        drawTables();
    }

    private void drawTables()
    {
        double startX = 220;
        double startY = 120;

        double x = startX;
        double y = startY;

        int i = 1;

        for (WaiterSimObject waiter : RestaurantSimObject.getWaiters())
        {
            for (Table table : waiter.getTables())
            {
                table.setCircle( drawTable(x, y) );
                x += 100;
                if (i % 6 == 0)
                {
                    x = startX;
                    y += 100;
                }

                i++;
            }
        }
    }

    private void prepareFramesForVisualisation(ObservableList<KeyFrame> frames)
    {
        long time = 200;


        for (Event event : VisualisationLog.events)
        {
            frames.add(new KeyFrame(Duration.millis(time), e -> event.action()));
            time += 400;
        }

        frames.add(new KeyFrame(Duration.millis(time), e -> label.setText("Koniec")));
    }

    private Circle drawTable(double x, double y)
    {
        Circle circle = new Circle(15, Color.GREEN);
        circle.relocate(x, y);

        canvas.getChildren().add(circle);

        return circle;
    }
}
