/*
 * ChangesList.java
 *
 * Created on January 10, 2004, 8:52 PM
 */

package deskit.monitors;

/**
 * 
 * @author Robert Zal, Dariusz Pierzchala
 */
public class ChangesList
{
    private java.util.Vector <Change> list;

    /** Creates a new instance of ChangesList */
    public ChangesList()
    {
        list = new java.util.Vector <Change> ();
    }

    public Change get(int index)
    {
        return (Change) list.get(index);
    }

    public int size()
    {
        return list.size();
    }

    public Change getLast()
    {
        return (Change) list.get(list.size() - 1);
    }

    public void add(Change ch)
    {
        list.add(ch);
    }

    public double getMeanFromTimeRange(double t1, double t2)
    {
        double result = 0;
        Change tmp;
        int count = 0;
        double t;
        for (int i = 0; i < size(); i++)
        {
            tmp = get(i);
            t = tmp.getTime();
            if (t >= t1)
            {
                if (t <= t2)
                {
                    count += 1;
                    result += tmp.getValue();
                }
                else
                {
                    result = result / count;
                    break;
                }
            }
        }
        return result;
    }

    public double getMaxFromTimeRange(double t1, double t2)
    {
        double result = Double.MIN_VALUE;
        int t1i = size();
        Change tmp;
        double t;
        for (int i = 0; i < size(); i++)
        {
            tmp = get(i);
            t = tmp.getTime();
            if (t >= t1)
            {
                t1i = i;
                break;
            }
        }
        for (int i = t1i; i < size(); i++)
        {
            tmp = get(i);
            t = tmp.getTime();
            if (t <= t2)
            {
                if (result < tmp.getValue())
                    result = tmp.getValue();
            }
            else
                break;
        }
        return result;
    }

    public double getMinFromTimeRange(double t1, double t2)
    {
        double result = Double.MAX_VALUE;
        int t1i = size();
        Change tmp;
        double t;
        for (int i = 0; i < size(); i++)
        {
            tmp = get(i);
            t = tmp.getTime();
            if (t >= t1)
            {
                t1i = i;
                break;
            }
        }
        for (int i = t1i; i < size(); i++)
        {
            tmp = get(i);
            t = tmp.getTime();
            if (t <= t2)
            {
                if (result > tmp.getValue())
                    result = tmp.getValue();
            }
            else
                break;
        }
        return result;
    }

}