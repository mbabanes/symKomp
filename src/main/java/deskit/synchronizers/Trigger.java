package deskit.synchronizers;

/**
 * Description: Trigger class
 * 
 * @author Robert Zal, Dariusz Pierzchala
 */
import java.util.LinkedList;

import deskit.SimActivity;
import deskit.SimObject;



public class Trigger
{

    private LinkedList <SimActivity> waitingList; //List of threads waiting for signal

    // przeciazone konstruktory pozwalajace dodac triggera do listy w simManagerze
    // potrzebne aby moc zakonczyc watki zatrzymane na tym triggerze
    public Trigger(SimObject simObject) {
		waitingList = new LinkedList<SimActivity>();
		simObject.simManager.addTriggerToTriggersList(this);
	}

    public Trigger(SimActivity simActivity) {
		waitingList = new LinkedList<SimActivity>();
		simActivity.simManager.addTriggerToTriggersList(this);
	}

    

	public void wait(SimActivity m) {
		synchronized (waitingList) {
			waitingList.add(m);
		}
	}

    public void fire() {
		synchronized (waitingList) {
			SimActivity m;
			while (waitingList.size() > 0) {
				m = (SimActivity) waitingList.removeFirst();
				m.resumeActivity();
			}
		}
	}

    public int getNumberOfThreads() {
		return waitingList.size();
	}

	public boolean interrupt(SimActivity m) {
		synchronized (waitingList) {
			boolean r = waitingList.remove(m);
			return r;
		}
	}

	public void fireAll() {
		while (waitingList.size() > 0) {
			synchronized (waitingList) {
				SimActivity m;
				while (waitingList.size() > 0) {
					m = (SimActivity) waitingList.removeFirst();
					m.terminate();
				}
			}
		}
	}
}

