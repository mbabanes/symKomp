package deskit;

import java.util.Comparator;

public class SimActivityCompare implements Comparator<Object> {

	public int compare(Object Obj1, Object Obj2) {
		SimActivity simActivity1, simActivity2;
		int tmp;

		simActivity1 = (SimActivity) Obj1;
		simActivity2 = (SimActivity) Obj2;
		double X = simActivity1.getTimeOfResume() - simActivity2.getTimeOfResume();
		
		if (X < 0) 
			tmp = -1;
		else 
			if (X > 0)
				tmp = 1;
			else 
				if (simActivity1.getMyState() == 'I')
					tmp = -1;
				else 
					if (simActivity2.getMyState() == 'I')
						tmp = 1;
					else
						tmp = 0;
		return tmp;
	}
}