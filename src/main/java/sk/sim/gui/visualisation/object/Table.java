package sk.sim.gui.visualisation.object;

import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Table
{
    private boolean free = true;

    private Circle circle;


    public void sit()
    {
        free = false;
    }

    public void makeFree()
    {
        free = true;
    }
}
