package deskit;

import java.util.Comparator;

public class SimObjectCompare implements Comparator<Object>{
	
	public int compare(Object obj1, Object obj2) {
        SimObject simObject1 = (SimObject) obj1;
        SimObject simObject2 = (SimObject) obj2;
        int tmp;
        
        if (simObject1.getActivityListSize() == 0) {
			if (simObject2.getActivityListSize() == 0)
				tmp = 0;
			else
				tmp = 1;
		}
        else {
            if (simObject2.getActivityListSize() == 0) {
				tmp = -1;
			}
            else {
                SimActivity simActivity1 = simObject1.getFirstSimActivityFromActivityList();
                SimActivity simActivity2 = simObject2.getFirstSimActivityFromActivityList();
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
            }
        }
        return tmp;
    }
}