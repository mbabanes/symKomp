package deskit;

import deskit.ActivityList;

public abstract class SimObject {

	public SimManager simManager = null;
	private ActivityList activityList = null;
    private SuspendedList suspendedList = null; //lista tymczasowo zawieszonych

	public SimObject() {
		initSimObject(SimManager.getSimManager());	
	}

	/**
	 * This must be overridden by user to initialize SimObject. 
	 * Do not use constructor for this purpose.
	 * (jest wywolana juz po stworzeniu beana przez Springa)
	 */
	//protected abstract void initialize();

	public void initSimObject(SimManager simManager) {
		this.simManager = simManager;
		activityList = new ActivityList();
        suspendedList = new SuspendedList();
		//initialize();
		this.simManager.addToPendingList(this);
	}

    public int getSuspendedListSize() {
        return suspendedList.size();
    }

	public int getActivityListSize() {
		return activityList.size();
	}

	public SimActivity getFirstSimActivityFromActivityList() {
		SimActivity simActivity = activityList.getFirst();
		return simActivity;
	}
    
    public SimActivity getFirstSimActivityFromSuspendedList() {
		SimActivity simActivity = suspendedList.getFirst();
		return simActivity;
	}

	public SimActivity takeFirstSimActivityFromActivityList() {
		return activityList.takeFirst();
	}

	public boolean addSimActivityToActivityList(SimActivity simActivity) {
		boolean pom = false;
		synchronized (activityList) {
			pom = activityList.add(simActivity);
			simManager.sortPendingList();
		}
		return pom;
	}

    public boolean addSimActivityToSuspendedList(SimActivity simActivity) {
		boolean pom = false;
		synchronized (suspendedList) {
			pom = suspendedList.add(simActivity);
		}
		return pom;
	}

	public boolean removeSimActivityFromActivityList(SimActivity simActivity) {
		boolean pom = false;
		synchronized (activityList) {
			pom = activityList.remove(simActivity);
			simManager.sortPendingList();
		}
		return pom;
	}

    public boolean removeSimActivityFromSuspendedList(SimActivity simActivity) {
		boolean pom = false;
		synchronized (suspendedList) {
			pom = suspendedList.remove(simActivity);
		}
		return pom;
	}

	public void sortActivityList() {
		synchronized (activityList) {
			activityList.sort();
			simManager.sortPendingList();
		}
	}

	public double getSimTime() {
		return simManager.getSimTime();
	}

	// ------------ Spring setters methods -------------------------------------
	public void setActivityList(ActivityList activityList) {
		this.activityList = activityList;
	}
}
