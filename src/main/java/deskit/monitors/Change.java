/*
 * Change.java
 *
 * Created on November 2, 2003, 10:03 PM
 */

package deskit.monitors;

/**
 * 
 * @author Robert Zal, Dariusz Pierzchala
 */

import deskit.SimManager;

/*
 * Description: This class holds value of variable to which belongs to in
 * specified time moment
 */
public class Change
{

    private double value;
    private double time;

    /** Creates a new instance of Change */
    public Change(double v, double t)
    {
        value = v;
        time = t;
    }

    public Change(double v, SimManager simManager)
    {
        value = v;
        time = simManager.getSimTime();
    }

    //Function giving as a result value (double)
    public double getValue()
    {
        return value;
    }

    //Function giving as a result time when the change happened
    public double getTime()
    {
        return time;
    }

    //Function for setting value of time
    public void setTime(double t)
    {
        time = t;
    }
}