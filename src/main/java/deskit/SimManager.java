package deskit;

import java.util.LinkedList;

import deskit.synchronizers.Trigger;


public class SimManager {

	// Singleton, zatem pole referencyjne
	private static final SimManager simMgr = new SimManager();
	//private ThreadPoolTaskExecutor taskExecutor;
	private PendingList pendingList= new PendingList();
    //zmienna pozwalajaca zatrzymac symulacje w dowolnej chwili
	protected Boolean running = false;
	//zmienna pozawalajaca zapauzowac symulacje w dowolnej chwili
	private Boolean paused = false;
    private double simTime = 0;
    private double stopTime = 0;
    private SyncSemaphore sem = new SyncSemaphore();
    private SyncSemaphore semPaused = new SyncSemaphore();
    private LinkedList<Trigger> triggersList = new LinkedList<Trigger>();
    
    public SimManager() {
	}
    
    public static final SimManager getSimManager()
    {
   		return simMgr;
    }
    
	public synchronized double getSimTime() {
		return simTime;
	}

	public void setSimTime(double simTime) {
		simMgr.simTime = simTime;
	}

	public double getStopTime() {
		return stopTime;
	}

	public void setStopTime(double stopTime) {
		simMgr.stopTime = stopTime;
	}

	public void addTriggerToTriggersList(Trigger trigger){
		triggersList.add(trigger);
	}
	
	// ------------ Simulation control methods --------------------------------
	public String startSimulation() {
		try {
			System.out.println();
			System.out.println("SimManager("+simMgr+"): Rozpoczeto symulacje");
			running = true;
			SimObject tmpObject;
			SimActivity tmpActivity;
			tmpObject = getFirstSimObjectFromPendingList();
			if (tmpObject != null) {
				if (tmpObject.getActivityListSize() > 0) {
					tmpActivity = tmpObject.getFirstSimActivityFromActivityList();
					setSimTime(tmpActivity.getTimeOfResume());
					tmpActivity.resumeActivity();
					sem.waitOnSem();
					System.out.println("SimManager("+simMgr+"): Zakonczono symulacje "+getSimTime());
					return "SimManager: zakonczono symulacje";
				} else {
					stopSimulation();	
				}
			} else {
				stopSimulation();
			}
			return "zakonczono symulacje";
		} catch (Exception e) {
			System.err.println("Manager.startSimulation Error: "+ e.getMessage());
			return "Manager.startSimulation Error: "+ e.getMessage();
		}
	}

	public String pauseSimulation(){
		paused = true;
		return "Simulation paused, (SimTime: "+simTime+")";
	}
	
	public String resumeSimulation(){
		paused = false;
		semPaused.signalSem();
		return "Simulation resumed, (SimTime: "+simTime+")";
	}
	
	public void stopSimulation() {
		running = false;
		terminateAllThreads();
		sem.signalSem();
	}

	public String stopSimulationImmediately(){
		running = false;
		semPaused.signalSem();
		return "Simulation aborted by user, (SimTime: "+simTime+")";
	}
	
    public boolean signal(SimActivity simActivity) {
		boolean result = false;
			
		if (paused) {
			System.out.println("SimManager: Pause, (SimTime: "+simTime+")");
			semPaused.waitOnSem();
			System.out.println("SimManager: Resume, (SimTime: "+simTime+")");
		}
		
		if (stopTime > 0) {
			if ((simTime <= stopTime) && (running)) {
				result = workStep(simActivity);
			} else {
				stopSimulation();
			}
		} else {
			if (running) {
				result = workStep(simActivity);
			} else {
				stopSimulation();
			}
		}
		return result;
	}
	
    private boolean workStep(SimActivity m) {
		boolean result = false;
		SimObject tmpObject;
		SimActivity tmpActivity;

		tmpObject = getFirstSimObjectFromPendingList();
		if (tmpObject != null) {
			if (tmpObject.getActivityListSize() > 0) {
				tmpActivity = tmpObject.getFirstSimActivityFromActivityList();
				setSimTime(tmpActivity.getTimeOfResume());
				if (tmpActivity != m) {
					tmpActivity.resumeActivity();
					result = true;
				} else {
					result = false;
				}
			} else {
				stopSimulation();
			}
		} else {
			stopSimulation();
		}
		return result;
	}
    
    
    private void terminateAllThreads(){
    	while(pendingList.size() > 0){
    		SimObject tmpObject;
    		
    		tmpObject = getFirstSimObjectFromPendingList();
    		while((tmpObject != null) && (tmpObject.getActivityListSize() > 0)){
    			SimActivity tmpActivity;
    			tmpActivity = tmpObject.getFirstSimActivityFromActivityList();
    			tmpActivity.terminate();
    			tmpObject.removeSimActivityFromActivityList(tmpActivity);
    		}
    		pendingList.remove(tmpObject);
    	}
    	System.out.println("terminate threads");
    	
    }
    

	// ------------ PendingList control methods --------------------------------
	public void addToPendingList(SimObject simObject) {
		synchronized (pendingList) {
			pendingList.add(simObject);
		}
	}
	
	public void removeFromPendingList(SimObject simObject) {
		pendingList.remove(simObject);
	}

	public void sortPendingList() {
		synchronized (pendingList) {
			pendingList.sort();
		} 
	}

	public SimObject getFirstSimObjectFromPendingList() {
		return pendingList.getFirstSimObject();
	}

	public void setPendingList(PendingList pendingList) {
		simMgr.pendingList = pendingList;
	}

}
