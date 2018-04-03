package deskit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PendingList {

	private List<SimObject> list = new ArrayList<SimObject>();
	private SimObjectCompare simObjectCompare = new SimObjectCompare();

	public boolean add(SimObject simObject) {
		boolean pom = list.add(simObject);
		sort();
		return pom;
	}

	public SimObject getFirstSimObject() {
		return (SimObject) list.get(0);
	}

	public SimActivity getFirstSimActivity() {
		return getFirstSimObject().getFirstSimActivityFromActivityList();
	}

	public boolean remove(SimObject simObject) {
		return list.remove(simObject);
	}

	public void removeAllElements() {
		list.clear();
	}

	public void sort() {
		Collections.sort(list, simObjectCompare);
	}

	public int size() {
		return list.size();
	}

	// ------------ Spring setters methods -------------------------------------
	public void setSimObjectCompare(SimObjectCompare simObjectCompare) {
		this.simObjectCompare = simObjectCompare;
	}
}
