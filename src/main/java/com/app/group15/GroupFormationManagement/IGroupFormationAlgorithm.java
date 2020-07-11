package com.app.group15.GroupFormationManagement;

import java.util.ArrayList;
import java.util.HashMap;

public interface IGroupFormationAlgorithm {
	
	
	public ArrayList<ArrayList> formGroups(ArrayList<StudentResponseMaintainer> studentResponseList, int groupSize,
			HashMap<Integer, String> questionCriteria);

}
