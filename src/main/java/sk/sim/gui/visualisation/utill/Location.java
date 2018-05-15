package sk.sim.gui.visualisation.utill;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location
{
    private double x;
    private double y;



    public Location(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
}
