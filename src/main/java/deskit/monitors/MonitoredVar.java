/*
 * MDouble.java
 *
 * Created on November 10, 2003, 2:45 PM
 */

package deskit.monitors;

import deskit.SimManager;
import deskit.SimObject;
import deskit.monitors.Change;
import deskit.monitors.ChangesList;
import deskit.monitors.Histogram;


/**
 * 
 * @author Robert Zal, Dariusz Pierzchala
 */

public class MonitoredVar
{

    private Histogram histogram;
    private ChangesList list; // list of changes of value
    private double actualval;
    private SimManager simMamager;

    /** Creates a new instance of MDouble */
    public MonitoredVar(SimObject simObject)
    {
    	simMamager = simObject.simManager;
    	list = new ChangesList();
    }

    public MonitoredVar(double val, SimObject simObject)
    {
    	simMamager = simObject.simManager;
    	actualval = val;
        list = new ChangesList();
        Change ch = new Change(val, simMamager);
        list.add(ch);
    }

    public void setValue(double nv)
    {// throws WrongTypeException{
        Change ch;
        ch = new Change(nv, simMamager);
        list.add(ch);
        actualval = nv;
    }

    public double getValue()
    {
        return actualval;
    }

    public Histogram getHistogram()
    {
        if (histogram == null)
        {
            histogram = new Histogram(list.size());
            try
            {
                for (int i = 0; i < list.size(); i++)
                    histogram.add(list.get(i).getValue());
                histogram.sort();
            }
            catch (Exception e)
            {
                System.out
                        .println("MonitoredVar.getHistogram() : Not enough memory, "
                                + e.getMessage());
            }
        }

        return histogram;
    }

    public ChangesList getChanges()
    {
        return list;
    }

}