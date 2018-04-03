/*
 * Histogram.java
 *
 * Created on November 2, 2003, 10:28 PM
 */

package deskit.monitors;

/**
 * 
 * @author Robert Zal, Dariusz Pierzchala
 */

public class Histogram
{

    private double[] hist;
    private int index;

    /** Creates a new instance of Histogram */
    public Histogram(int n)
    {
        //  hist = new LinkedList();
        hist = new double[n];
        index = 0;
    }

    public void add(double e)
    {
        hist[index] = e;
        index += 1;
        // hist.add(e);
    }

    public void sort()
    {
        //       Collections.sort(hist);
        qsort(0, size() - 1);
    }

    void qsort(int l, int p)
    {
        int m;
        double h;
        m = l;
        if (l < p)
        {
            for (int i = l + 1; i <= p; i++)
                if (hist[i] < hist[l])
                {
                    m = m + 1;
                    h = hist[m];
                    hist[m] = hist[i];
                    hist[i] = h;
                }
            h = hist[l];
            hist[l] = hist[m];
            hist[m] = h;
            qsort(l, m - 1);
            qsort(m + 1, p);

        }
    }

    public double get(int i)
    {
        return hist[i];
    }

    public int size()
    {
        return hist.length;
    }

    /*
     * numbers of elements from range (a,b]
     */
    public int getNumberFromRange(double a, double b)
    {
        int result = 0;
        double c = get(0);
        try
        {
            for (int i = 0; i < hist.length; i++)
            {
                c = get(i);
                if (c > a)
                {
                    if (c <= b)
                    {
                        result += 1;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.err.println("Error in method getNumberFromRange !");
        }
        return result;
    }

    public int[] getGroupedHistogram(int liczbaPrzedzialow)
    {
        int[] result = new int[liczbaPrzedzialow];
        double d = (getMaxValue() - getMinValue()) / liczbaPrzedzialow;
        int rangeNb = 0;
        double tmpv;
        double r = getMinValue() + d;
        for (int i = 0; i < size(); i++)
        {
            tmpv = get(i);
            if (tmpv > r)
            {
                r = r + d;
                if (rangeNb < liczbaPrzedzialow - 1)
                    rangeNb += 1;
            }
            result[rangeNb] += 1;
        }
        return result;
    }

    public double getMinValue()
    {
        return get(0);
    }

    public double getMaxValue()
    {
        return get(hist.length - 1);
    }

}