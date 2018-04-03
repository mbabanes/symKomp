/*
 * Aplication.java
 *
 * Created on November 30, 2003, 8:20 PM
 */

package deskit;

/**
 * 
 * @author Robert Zal, Dariusz Pierzchala
 */
public abstract class Application
{ 
	SimManager manager;

    /** Creates a new instance of Aplication */
    public Application()
    {
    	manager = new SimManager();
    }

    public void callActivity(SimObject paren, SimActivity method, double time)
    {
        SimActivity.callActivity(paren, method, time);
        /*
         * if ((method.getParent()!=null)&&(method.getParent()!=paren)) {
         * System.err.println("Method has parent SimObject ascribed"); }else{
         * method.setParent(paren); method.start(time); }
         */
    }

    public void callActivity(SimObject paren, SimActivity method)
    {
        SimActivity.callActivity(paren, method);
        /*
         * if ((method.getParent()!=null)&&(method.getParent()!=paren)){
         * System.err.println("Method has parent SimObject ascribed"); }else{
         * method.setParent(paren); method.start(0); }
         */
    }

    public void startSimulation()
    {
        manager.startSimulation();
    }

}