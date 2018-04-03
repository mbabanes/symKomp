/*
 * DiagramForm.java
 *
 * Created on January 11, 2004, 2:02 PM
 */

package deskit.monitors;

/**
 * 
 * @author Robert Zal, Dariusz Pierzchala
 */

import java.awt.*;
import java.util.Vector;

public class Diagram extends javax.swing.JFrame
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6863523738132832595L;
	private Vector v; //vector of monitored variables
    private Vector color; //vector of colors to use
    private String type;
    private Vector histogramV;
    //parametry okreslajace obszar gdzie rysowac wykrest a gdzie inne elementy
    private int xmin;
    private int xmax;
    private int ymin;
    private int ymax;
    private double maxValue;
    private double minValue;
    private double deltaValue;
    private double deltax, deltay;
    private double minNumber, maxNumber;
    private int liczbaPrzedzialow = 50;
    private java.text.NumberFormat nf;
    //zmienne string do opisu osi wykresu
    private String maxNumberStr;
    private String minNumberStr;
    private String maxValueStr;
    private String minValueStr;

    /** Creates new form DiagramForm */
    public Diagram(String tp, String title)
    {
        boolean ok = false;
        if (tp.equalsIgnoreCase("dystrybuanta"))
        {
            type = "dystrybuanta";
            ok = true;
        }
        else
            if (tp.equalsIgnoreCase("histogram"))
            {
                type = "histogram";
                ok = true;
                histogramV = new Vector();
            }
            else
                if (tp.equalsIgnoreCase("zaleznoscCzasowa"))
                {
                    type = "zaleznoscCzasowa";
                    ok = true;
                }
        if (ok)
        {
            initComponents();
            setTitle(title + " : " + tp);
            v = new Vector();
            color = new Vector();
            xmin = 31;
            ymin = 61;
            xmax = getWidth() - 81;
            ymax = getHeight() - 51;
            deltax = xmax - xmin;
            deltay = ymax - ymin;
            maxNumber = 0;
            minNumber = 0;
        }
        else
            System.err.println("No such type of digram as " + tp);

        nf = java.text.NumberFormat.getInstance();
        nf.setMaximumFractionDigits(5);
    }

    public void show()
    {
        if (v.size() > 0)
        {
            if (type.equalsIgnoreCase("histogram"))
            {
                MonitoredVar mv;
                int[] histValues;
                for (int j = 0; j < v.size(); j++)
                { //for each variable
                    mv = (MonitoredVar) v.get(j);
                    histValues = mv.getHistogram().getGroupedHistogram(
                            liczbaPrzedzialow);
                    histogramV.add(histValues);
                    for (int i = 0; i < liczbaPrzedzialow - 1; i++)
                    {
                        if (maxNumber < histValues[i])
                            maxNumber = histValues[i];
                    }
                }
            }
            super.show();
        }
        else
            System.err.println("Show diagram : Nothing to draw!");
    }

    public void add(MonitoredVar mv, Color whatColor)
    {
        double min = Statistics.min(mv);
        double max = Statistics.max(mv);
        double mint = mv.getChanges().get(0).getTime();
        double maxt = mv.getChanges().getLast().getTime();
        if (type.equalsIgnoreCase("dystrybuanta"))
        {
            if (v.size() > 0)
            {
                if (maxValue < max)
                    maxValue = max;
                if (minValue > min)
                    minValue = min;
                deltaValue = maxValue - minValue;
            }
            else
            {
                maxValue = max;
                minValue = min;
                maxNumber = 1;
                deltaValue = maxValue - minValue;
            }
            v.add(mv);
            color.add(whatColor);
        }
        else
            if (type.equalsIgnoreCase("zaleznoscCzasowa"))
            {
                if (v.size() > 0)
                {
                    if (maxValue < maxt)
                        maxValue = maxt;
                    if (minValue > mint)
                        minValue = mint;
                    if (minNumber > min)
                        minNumber = min;
                    if (maxNumber < max)
                        maxNumber = max;
                    deltaValue = maxValue - minValue;
                }
                else
                {
                    maxValue = maxt;
                    minValue = mint;
                    maxNumber = max;
                    minNumber = min;
                    deltaValue = maxValue - minValue;
                }
                v.add(mv);
                color.add(whatColor);
            }
            else
                if (type.equalsIgnoreCase("histogram"))
                {
                    if (v.size() > 0)
                    {
                        if (maxValue < max)
                            maxValue = max;
                        if (minValue > min)
                            minValue = min;
                        deltaValue = maxValue - minValue;
                    }
                    else
                    {
                        maxValue = max;
                        minValue = min;
                        deltaValue = maxValue - minValue;
                    }
                    v.add(mv);
                    color.add(whatColor);
                }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents()
    {//GEN-BEGIN:initComponents

        getContentPane().setLayout(null);

        setTitle("Wykres");
        addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                resizeWindow(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                exitForm(evt);
            }
        });

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
                .getScreenSize();
        setBounds((screenSize.width - 450) / 2, (screenSize.height - 350) / 2,
                450, 350);
    }//GEN-END:initComponents

    private void resizeWindow(java.awt.event.ComponentEvent evt)
    {//GEN-FIRST:event_resizeWindow
        // Add your handling code here:
        if (getWidth() < 450)
            setSize(450, getHeight());
        if (getHeight() < 350)
            setSize(getWidth(), 350);
        xmax = getWidth() - 81;
        ymax = getHeight() - 51;
        deltax = xmax - xmin;
        deltay = ymax - ymin;
        Graphics g = this.getGraphics();
        if (g != null)
            g.clearRect(0, 0, getWidth(), getHeight());
    }//GEN-LAST:event_resizeWindow

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt)
    {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private int x(double xv)
    {
        double tmp = (xv - minValue) * deltax / deltaValue + xmin;
        float tmp2 = Math.round(tmp);
        return Math.round(tmp2);
    }

    private int y(double yv)
    {
        double tmp = ymax - (yv - minNumber)
                * (deltay / (maxNumber - minNumber));
        float tmp2 = Math.round(tmp);
        return Math.round(tmp2);
    }

    private void rysujDystrybuante(Graphics g, MonitoredVar mv, Color c)
    {
        g.setColor(c);
        Histogram h = mv.getHistogram();
        int n = mv.getChanges().size();
        double actualCountD = 0;
        int lastCount = 0, actualCount = ymax;
        double actualValD;
        int lastVal, actualVal = xmin - 8;
        for (int i = 0; i < h.size(); i++)
        {
            lastVal = actualVal;
            actualValD = h.get(i);
            actualVal = x(actualValD);
            lastCount = actualCount;
            actualCountD += 1; //h.getElement(i).getNumber();
            actualCount = y(actualCountD / n);
            g.drawLine(lastVal, lastCount, actualVal, lastCount);
            g.drawLine(actualVal, lastCount, actualVal, actualCount);
        }
        g.drawLine(actualVal, actualCount, xmax + 8, actualCount);
    }

    private void rysujZaleznoscCzasowa(Graphics g, MonitoredVar mv, Color c)
    {
        g.setColor(c);
        ChangesList chl = mv.getChanges();
        Change ch = chl.get(0);
        double lastt, actualt = ch.getTime();
        double lastval, actualval = ch.getValue();
        g.drawOval(x(actualt) - 2, y(actualval) - 2, 4, 4);
        for (int i = 1; i < chl.size(); i++)
        {
            lastval = actualval;
            lastt = actualt;
            ch = chl.get(i);
            actualval = ch.getValue();
            actualt = ch.getTime();
            g.drawLine(x(lastt), y(lastval), x(actualt), y(actualval));
            g.drawOval(x(actualt) - 2, y(actualval) - 2, 4, 4);
        }

    }

    private void rysujHistogram(Graphics g, MonitoredVar mv, int[] histValues,
            Color c)
    {
        g.setColor(c);
        int x1 = xmin;
        int xstep = Math.round((xmax - xmin) / liczbaPrzedzialow);
        int x2 = x1 + xstep;
        for (int i = 0; i < liczbaPrzedzialow; i++)
        {
            g.drawLine(x1, y(histValues[i]), x2, y(histValues[i]));
            g.drawLine(x1, y(0), x1, y(histValues[i]));
            g.drawLine(x2, y(0), x2, y(histValues[i]));
            x1 = x2;
            x2 = x1 + xstep;
        }

    }

    public void paint(Graphics g)
    {
        g.setPaintMode();
        g.drawRect(xmin - 2, ymin - 1, xmax - xmin + 4, ymax - ymin + 2);
        MonitoredVar mv;
        Color c;
        if (type.equalsIgnoreCase("dystrybuanta"))
            for (int i = 0; i < v.size(); i++)
            {
                mv = (MonitoredVar) v.get(i);
                c = (Color) color.get(i);
                rysujDystrybuante(g, mv, c);
            }
        else
            if (type.equalsIgnoreCase("zaleznoscCzasowa"))
                for (int i = 0; i < v.size(); i++)
                {
                    mv = (MonitoredVar) v.get(i);
                    c = (Color) color.get(i);
                    rysujZaleznoscCzasowa(g, mv, c);
                }
            else
                if (type.equalsIgnoreCase("histogram"))
                {
                    int[] hv;
                    for (int i = 0; i < v.size(); i++)
                    {
                        mv = (MonitoredVar) v.get(i);
                        c = (Color) color.get(i);
                        hv = (int[]) histogramV.get(i);
                        rysujHistogram(g, mv, hv, c);
                    }
                }
        // rysuj opis

        if (v.size() == 1)
        {
            maxNumberStr = String.valueOf((float) maxNumber);
            //if (maxNumberStr.length()>8) maxNumberStr =
            // maxNumberStr.substring(0,8);
            minNumberStr = String.valueOf((float) minNumber);
            //if (minNumberStr.length()>8) minNumberStr =
            // minNumberStr.substring(0,8);
            maxValueStr = nf.format(maxValue);//String.valueOf((float)maxValue);
            //if (maxValueStr.length()>8) maxValueStr =
            // maxValueStr.substring(0,8);
            //minValueStr = String.valueOf((float)minValue);
            minValueStr = nf.format(minValue);
            //if (minValueStr.length()>8) minValueStr =
            // minValueStr.substring(0,8);
            g.setColor(Color.BLACK);
            g.drawString("" + maxNumberStr, xmax + 10, ymin + 2);
            g.drawString("" + minNumberStr, xmax + 10, ymax + 2);
            g.drawString("" + minValueStr, xmin - 25, ymax + 30);
            g.drawString("" + maxValueStr, xmax - 25, ymax + 30);
            /*
             * g.drawString(""+maxNumberStr,xmin-60,ymin+2);
             * g.drawString(""+minNumberStr,xmin-60,ymax+2);
             * g.drawString(""+minValueStr,xmin-12,ymax+14);
             * g.drawString(""+maxValueStr,xmax-12,ymax+14);
             */}/*
                 * else{ g.drawString(""+maxNumberStr,xmin-40,ymin-5);
                 * g.drawString(""+minNumberStr,xmin-40,ymax-5);
                 * g.drawString(""+minValueStr,xmin-10,ymax+4);
                 * g.drawString(""+maxValueStr,xmax-10,ymax+4); }
                 */

    }
}