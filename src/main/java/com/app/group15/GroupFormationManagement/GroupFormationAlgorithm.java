package com.app.group15.GroupFormationManagement;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class GroupFormationAlgorithm {
	
	
	
	public abstract ArrayList<ArrayList> formGroups(ArrayList<StudentResponseMaintainer> studentResponseList, int groupSize,
			HashMap<Integer, String> questionCriteria);
	
	//public abstract void getAllStudentResponsesForACourseSurvey(int courseId) ;

}
