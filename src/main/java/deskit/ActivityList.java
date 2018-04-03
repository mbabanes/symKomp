package deskit;

import java.util.Collections;
import java.util.LinkedList;

public class ActivityList {

	private LinkedList<SimActivity> list = new LinkedList<SimActivity>();
	private SimActivityCompare simActivityCompare = new SimActivityCompare();
	
	public void sort() {
		Collections.sort(list, simActivityCompare);
	}

	public boolean add(SimActivity simActivity) {
		boolean pom = list.add(simActivity);
		sort();
		return pom;
	}

	public boolean remove(SimActivity simActivity) {
		boolean pom = list.remove(simActivity);
		sort();
		return pom;
	}

	public SimActivity getFirst() {
		SimActivity result = null;
		result = (SimActivity) list.getFirst();
		return result;
	}

	public SimActivity takeFirst() {
		SimActivity result = null;
		result = (SimActivity) list.removeFirst();
		return result;
	}

	public int size() {
		return list.size();
	}
	
	// ------------ Spring setters methods -------------------------------------
	public void setSimActivityCompare(SimActivityCompare simActivityCompare) {
		this.simActivityCompare = simActivityCompare;
	}
}
