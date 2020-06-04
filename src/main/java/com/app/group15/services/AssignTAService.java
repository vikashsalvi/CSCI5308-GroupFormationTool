package com.app.group15.services;


import com.app.group15.dao.CourseAbstractDao;
import com.app.group15.dao.CourseInstructorMapperAbstractDao;
import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserRoleAbstractDao;
import com.app.group15.injectors.CourseDaoInjectorService;
import com.app.group15.injectors.CourseInstructorMapperDaoInjectorService;
import com.app.group15.injectors.UserDaoInjectorService;
import com.app.group15.injectors.UserRoleDaoInjectorService;
import com.app.group15.model.Course;
import com.app.group15.model.User;

import java.util.ArrayList;
import java.util.Set;

public class AssignTAService {

    private UserAbstractDao userDao = new UserDaoInjectorService().getUserDao();
    private CourseAbstractDao courseDao = new CourseDaoInjectorService().getCourseDao();
    private UserRoleAbstractDao userRoleDao = new UserRoleDaoInjectorService().getUserRoleDao();
    private CourseInstructorMapperAbstractDao courseInstructorMapperDao = new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();


    public boolean performTAUpdate(String bannerId, int courseId)
    {
        User userEntity= userDao.getUserByBannerId(bannerId);

        if (validateBannerID(bannerId) && validateCourseID(courseId)) {
            System.out.println("HERE");
            if (InstructorService.validateUserToAddAsTa(userEntity,courseId)){
				InstructorService.addOrUpdateStudentRole(userEntity,"TA");
				courseInstructorMapperDao.addTaToACourse(courseId,userEntity.getId());
				return true;
			} else {
            	return false;
			}
        }else {
            return false;
        }
    }

    public  boolean validateBannerID(String bannerId) {

        Set<String> roleSet = userRoleDao.getRolesByBannerId(bannerId);
        if (roleSet.contains("STUDENT")) {
            return true;
        }else {
            return false; }
    }

    public boolean validateCourseID(int courseId) {

        if (courseDao.get(courseId) != null) {
            return true;
        }else {
            return false;
        }
    }

    public boolean checkIntructorPermission(int instructorId, int courseId) {

        boolean returnVar = false;
        ArrayList<Course> courseEntitiesList = courseInstructorMapperDao.getCoursesByInstructor(instructorId);

        for (Course courseEntity : courseEntitiesList) {
            if (courseEntity.getId() == courseId) {
                returnVar = true;
                break;
            }
        }
        return returnVar;
    }

}
