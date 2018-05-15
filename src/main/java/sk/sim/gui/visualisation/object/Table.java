package sk.sim.gui.visualisation.object;

import lombok.Getter;
import sk.sim.gui.visualisation.utill.Location;
import sk.sim.objects.WaiterSimObject;

@Getter
public class Table
{
    private WaiterSimObject waiter;
    private boolean free = true;

    private Location location;


    public void sit()
    {
        free = false;
    }

    public void makeFree()
    {
        free = true;
    }
}
