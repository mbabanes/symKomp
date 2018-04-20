package deskit;

import java.util.concurrent.TimeUnit;

import deskit.SyncSemaphore;
import deskit.synchronizers.Semaphore;
import deskit.synchronizers.Trigger;



abstract public class SimActivity extends Thread {//implements Runnable{

	public SimManager simManager = null;
	private char state;
	private boolean interrupted = false;
	private boolean stopped = false;
	private SyncSemaphore semWaitFor;
    private SyncSemaphore semWaitForResume;
    private SyncSemaphore semRun;
    private Semaphore semaphore = null;
    private Trigger trigger = null;
    private double timeOfResume;
    private SimObject parentSimObject = null;
    private SimActivity parentSimActivity = null;
    private SimActivity executedActivity = null;
    
    

	public SimActivity(){
        semWaitFor = new SyncSemaphore();
        semWaitForResume = new SyncSemaphore();
        semRun = new SyncSemaphore();		
		state = 'N';
    }
    
	
	/**
	 * This must be overridden by user to initialize SimActivity.
	 * Do not use constructor for this purpose. 
	 */
	//protected abstract void initialize();
	
	private void initSimActivity(SimObject parentSimObject) {
		simManager = parentSimObject.simManager;
		timeOfResume = simManager.getSimTime();
		//initialize();
	}
	
	public double simTime() {
		return simManager.getSimTime();
	}
	
    public double getTimeOfResume() {
		return timeOfResume;
	}

	public void setTimeOfResume(double timeOfResume) {
		this.timeOfResume = timeOfResume;
	}

	public char getMyState() {
		return state;
	}
    
	public boolean isInterrupted() {
		return interrupted;
	}
    
	/**
	 * Returns if thread is stopped and should leave the the loop. 
	 * Use it after all wait.. functions
	 * @example
	 * if (isStopped()) break;
	 */
	public boolean isStopped(){
    	return stopped;
    }
	
    public SimObject getParentSimObject() {
		return parentSimObject;
	}

	public boolean setParentSimObject(SimObject simObject) {
		if (parentSimObject == null) {
			parentSimObject = simObject;
			return true;
		} else
			return false;
	}
	
	public SimActivity getParentSimActivity() {
		return parentSimActivity;
	}

	public void setParentSimActivity(SimActivity simActivity) {
		if (simActivity != this)
			parentSimActivity = simActivity;
		else
			parentSimActivity = null;
	}
	
	public SimActivity getExecutedSimActivity() {
        return executedActivity;
    }
	
    
    /**
	 * This function should be overridden by user
	 */
    abstract public void action();
   
    
    public void run() {
//		System.out.println("Watek rozpoczety: " + Thread.currentThread().getName());
		while (!stopped) {
			action();
			parentSimObject.removeSimActivityFromActivityList(this);

            //dodac do listy zawieszonych tymczasowo aktywnosci w simobject
            parentSimObject.addSimActivityToSuspendedList(this);

			interrupted = false;
			state = 'F'; // finished
			if (stopped) break;
			if (parentSimActivity != null){
				parentSimActivity.resumeActivity(); //dla callActivity
			} else {
				simManager.signal(this); //dla waitForActivity
			}
			if (stopped) break;
			semRun.waitOnSem();
		}
//		System.out.println("--------> SimActivity: Watek zakonczony " + Thread.currentThread().getName());
	}
	
    public void start() {
		state = 'E';
		//simManager.executeThread(this);
		super.start();
	}

	private void start(double time) {
		timeOfResume = simManager.getSimTime() + time;

        //usuniecie z listy zawieszonych tymczasowo simobject;
        parentSimObject.removeSimActivityFromSuspendedList(this);

		parentSimObject.addSimActivityToActivityList(this);
	}

    public void resumeActivity() {
		switch (state) {

		case 'N':
			start();
			break;
		case 'F': {
			try {
				TimeUnit.MILLISECONDS.sleep(4);
			} catch (Exception e) {
				System.err.println("Resume Method from state F: Error on waiting");
			}
			semRun.signalSem();
		}
			break;

		case 'R': // ready to execute
		case 'I': // interrupted
		case 'D': { // wait duration
			semWaitForResume.signalSem();
		}
			break;

		case 'M': {
			parentSimObject.addSimActivityToActivityList(this);
			state = 'R';
			executedActivity = null;
			timeOfResume = simTime();
			semWaitFor.signalSem();
		}
			break;

		case 'T': {
			semWaitFor.signalSem();
			parentSimObject.addSimActivityToActivityList(this);
			state = 'R';
			timeOfResume = simTime();
			trigger = null;
		}
			break;
		case 'S': {
			semWaitFor.signalSem();
			parentSimObject.addSimActivityToActivityList(this);
			state = 'R';
			timeOfResume = simTime();
			semaphore = null;
		}
			break;

		default: {
			System.err.println("Error: Method cannot be resumed, unexpected state of method! "+ state);
		}
		}
	}
	
	public void terminate() {
		parentSimObject.removeSimActivityFromActivityList(this);

        //removeSimActivityFromSuspendedList dla drugiej listy - jesli nie usunal z powyzszej
        parentSimObject.removeSimActivityFromSuspendedList(this);

		stopped = true;
		semWaitFor.signalSem();
		if (trigger != null) trigger.interrupt(this);
		//semWaitForResume.signalSem();
		semWaitForResume.signalSem();
		//semWaitFor.signalSem();
		semRun.signalSem();
		state = 'F';//'E';
	}
    
	public void interruptAndStop() {
		interrupt();
		parentSimObject.removeSimActivityFromActivityList(this);
		if (state == 'E') {
			simManager.signal(this);
		}
		stopped = true;
    }
    
    public void interrupt() {
		switch (state) {
		case 'E': {

		}
			break;
		case 'M': {
			interrupted = true;
			state = 'I';
			semWaitFor.signalSem();
			timeOfResume = simTime();
			parentSimObject.addSimActivityToActivityList(this);
			executedActivity.interruptAndStop();
			executedActivity = null;
		}
			break;
		case 'D': {
			interrupted = true;
			state = 'I';
			timeOfResume = simTime();
			parentSimObject.sortActivityList();
		}
			break;
		case 'T': {
			interrupted = true;
			state = 'I';
			semWaitFor.signalSem();
			timeOfResume = simTime();
			parentSimObject.addSimActivityToActivityList(this);
			trigger.interrupt(this);
			trigger = null;
		}
			break;
		case 'S': {
			interrupted = true;
			state = 'I';
			semWaitFor.signalSem();
			timeOfResume = simTime();
			parentSimObject.addSimActivityToActivityList(this);
			semaphore.interrupt(this);
			semaphore = null;
		}
			break;

		default: {
			System.err.println("Error: Method cannot be interrupted, unexpected state of method!");
		}
		}

	}
    
    
    /**
     * This function can be overridden by user and describes what should happen when
     * SimActivity is interrupted
     */
    protected void onInterrupt(){}

    public static void callActivity(SimObject parentSimObject, SimActivity activity) {
		if (activity.getMyState() == 'N') {
			activity.setParentSimObject(parentSimObject);
			activity.initSimActivity(parentSimObject);
			activity.start(0);
		} else if (activity.getMyState() == 'F') {
			try {
				activity.start(0);
			} catch (Exception e) {
				System.err.println("Cannot again execute method, "+ e.getMessage());
			}
		}
	}
    
    public static void callActivity(SimObject parentSimObject, SimActivity activity, double time) {
		if (activity.getMyState() == 'N') {
			activity.setParentSimObject(parentSimObject);
			activity.initSimActivity(parentSimObject);
			activity.start(time);
		} else if (activity.getMyState() == 'F') {
			activity.start(time);
		}
	}
    
    protected void waitDuration(double time) {
    	interrupted = false;
		if (time > 0) {
			timeOfResume = simManager.getSimTime() + time;
			parentSimObject.sortActivityList();
			state = 'D';
			synchronized (semWaitForResume) {
				if (simManager.signal(this)) {
					semWaitForResume.waitOnSem();
				}
			}
			if (state == 'I') {
				onInterrupt();
			}
			state = 'E';
		}
	}
    
    protected void waitOnSemaphore(Semaphore sem) {
		interrupted = false;
		if (sem != null) {
			semaphore = sem;
			synchronized (semWaitFor) {
				synchronized (semWaitForResume) {
					if (semaphore.wait(this)) {
						state = 'S';
						parentSimObject.removeSimActivityFromActivityList(this);
						if (simManager.signal(this)) {
							semWaitFor.waitOnSem();
						}

						semWaitForResume.waitOnSem(); // after fire must wait
						// for its turn
						if (state == 'I') {
							onInterrupt();
						}
					}
					state = 'E';
				}
			}
		}
	}
    
    protected void waitForFire(Trigger trg) {
    	interrupted = false;
		if (!(trg == null)) {
			trigger = trg;
			synchronized (semWaitFor) {
				synchronized (semWaitForResume) {
					state = 'T';
					parentSimObject.removeSimActivityFromActivityList(this);
					trigger.wait(this);
					if (simManager.signal(this)) {
						semWaitFor.waitOnSem();
					}
					semWaitForResume.waitOnSem(); // after fire must wait for
					// its turn
					if (state == 'I') {
						onInterrupt();
					}
					state = 'E';
				}
			}
		}
	}
    
    
    protected void waitForActivity(SimObject paren, SimActivity method) {
    	interrupted = false;
		if ((method == null) || (paren == null))
			System.err
					.println("Error : waitFor : null pointer to method or parent object.");
		else {
			if (method.getMyState() == 'N') {
				executedActivity = method;
				method.setParentSimObject(paren);
				method.setParentSimActivity(this);
				synchronized (semWaitFor) {
					state = 'M';
					parentSimObject.removeSimActivityFromActivityList(this);
					method.start(0);
					if (simManager.signal(this)) {
						semWaitFor.waitOnSem(); // released after finished run()
					}
					// executed method
					if (state == 'I') {
						synchronized (semWaitForResume) {
							semWaitForResume.waitOnSem();
							onInterrupt();
						}
						//dodatkowa jesli jest podana jako parametr
					} else if (simManager.signal(this)) {
						semWaitForResume.waitOnSem();
					}
				}
				state = 'E';
			} else {
				System.err.println("Method must be in state 'N' for executing");
			}
		}
	}
    
	
	// ------------ Spring setters methods -------------------------------------
	public void setState(char state) {
		this.state = state;
	}    
    public void setSemWaitFor(SyncSemaphore semWaitFor) {
		this.semWaitFor = semWaitFor;
	}
	public void setSemWaitForResume(SyncSemaphore semWaitForResume) {
		this.semWaitForResume = semWaitForResume;
	}
	public void setSemRun(SyncSemaphore semRun) {
		this.semRun = semRun;
	}
}
