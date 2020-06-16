package com.app.group15.courseManagement;



import com.app.group15.userManagement.IUserService;
import com.app.group15.userManagement.User;
import com.app.group15.userManagement.UserAbstractDao;
import com.app.group15.userManagement.UserRoleAbstractDao;

import java.util.ArrayList;
import java.util.Set;

public class AssignTAService  implements IAssignTAService,IAssignTaServiceInjector{
	
	private UserAbstractDao userDao;
    private CourseAbstractDao courseDao;
    private UserRoleAbstractDao userRoleDao;
    private CourseInstructorMapperAbstractDao courseInstructorMapperDao ;
    private IInstructorService instructorService;
    private IUserService userService;
    private ICourseService courseService;


    public boolean performTAUpdate(String bannerId, int courseId)
    {
        User userEntity= userDao.getUserByBannerId(bannerId);

        if (userService.validateBannerID(bannerId) && courseService.validateCourseID(courseId)) {
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

    @Override
    public void injectUserService(IUserService userService) {
        this.userService=userService;
    }

    @Override
    public void injectCourseService(CourseService courseService) {
        this.courseService=courseService;
    }


}
