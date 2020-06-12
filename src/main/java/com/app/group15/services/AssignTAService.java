package com.app.group15.services;


import com.app.group15.config.AppConfig;
import com.app.group15.config.ServiceConfig;
import com.app.group15.dao.CourseAbstractDao;
import com.app.group15.dao.CourseInstructorMapperAbstractDao;
import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserRoleAbstractDao;
import com.app.group15.injectors.dao.CourseDaoInjectorService;
import com.app.group15.injectors.dao.CourseInstructorMapperDaoInjectorService;
import com.app.group15.injectors.dao.UserDaoInjectorService;
import com.app.group15.injectors.dao.UserRoleDaoInjectorService;
import com.app.group15.injectors.service.IAssignTaServiceInjector;
import com.app.group15.model.Course;
import com.app.group15.model.User;

import java.util.ArrayList;
import java.util.Set;

public class AssignTAService  implements IAssignTAService,IAssignTaServiceInjector{
	
	private UserAbstractDao userDao;
    private CourseAbstractDao courseDao;
    private UserRoleAbstractDao userRoleDao;
    private CourseInstructorMapperAbstractDao courseInstructorMapperDao ;
    private IInstructorService instructorService;

    

    public boolean performTAUpdate(String bannerId, int courseId)
    {
        User userEntity= userDao.getUserByBannerId(bannerId);

        if (validateBannerID(bannerId) && validateCourseID(courseId)) {
            System.out.println("HERE");
            if (instructorService.validateUserToAddAsTa(userEntity,courseId)){
            	instructorService.addOrUpdateStudentRole(userEntity,"TA");
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

	@Override
	public void injectUserDao(UserAbstractDao userDao) {
		this.userDao=userDao;
		
	}

	@Override
	public void inectCourseDao(CourseAbstractDao courseDao) {
		this.courseDao=courseDao;
		
	}

	@Override
	public void injectUserRoleDao(UserRoleAbstractDao userRoleDao) {
		this.userRoleDao=userRoleDao;
		
	}

	@Override
	public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao) {
		this.courseInstructorMapperDao=courseInstructorMapperDao;
		
	}

	@Override
	public void injectInstructorService(IInstructorService instructorService) {
		this.instructorService=instructorService;
		
	}

}
