package deskit.synchronizers;

/**
 * Description: semaphor used for synchronization of threads
 * 
 * @author: Robert Zal, Dariusz Pierzchala
 *  
 */

import java.util.LinkedList;

import deskit.SimActivity;



public class Semaphore
{

    private int maxCapacity; // number of threads allowed to go through in the
    // same time
    private int actualCapacity; // number of threads which can be allowed to go
    // through yet

    private LinkedList <SimActivity> waitingList; //List of threads waiting for signal

    public Semaphore() { // create binary semaphore
		waitingList = new LinkedList<SimActivity>();
		maxCapacity = 1;
		actualCapacity = 1;
	}

    public Semaphore(int size) {
		waitingList = new LinkedList<SimActivity>();
		if (size > 0) {
			maxCapacity = size;
			actualCapacity = size;
		} else {
			maxCapacity = 1;
			actualCapacity = 1;
		}
	}

    public synchronized boolean wait(SimActivity m) {
		if (actualCapacity > 0) {
			actualCapacity -= 1;
			return false;
		} else {
			waitingList.addLast(m);
			return true;
		}
	}

    public synchronized SimActivity signal() {
		SimActivity m;
		if (waitingList.size() > 0) {
			m = (SimActivity) waitingList.removeFirst();
			m.resumeActivity();
			return m;
		} else {
			if (actualCapacity < maxCapacity)
				actualCapacity += 1;
			return null;
		}
	}

    public int getNumberOfWaitingMethods() {
		return waitingList.size();
	}

	public int getSemaphorCapasity() {
		return maxCapacity;
	}

	public boolean interrupt(SimActivity m) {
		boolean r = waitingList.remove(m);
		return r;
	}
}