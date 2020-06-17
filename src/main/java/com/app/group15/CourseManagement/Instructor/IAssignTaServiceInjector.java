package com.app.group15.CourseManagement.Instructor;

import com.app.group15.CourseManagement.CourseAbstractDao;
import com.app.group15.CourseManagement.CourseService;
import com.app.group15.UserManagement.IUserService;
import com.app.group15.UserManagement.UserAbstractDao;
import com.app.group15.UserManagement.UserRoleAbstractDao;

public interface IAssignTaServiceInjector {

    public void injectUserDao(UserAbstractDao userDao);

    public void injectCourseDao(CourseAbstractDao courseDao);

    public void injectUserRoleDao(UserRoleAbstractDao userRoleDao);

    public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao);

    public void injectInstructorService(IInstructorService instructorService);

    public void injectUserService(IUserService userService);

    public void injectCourseService(CourseService courseService);

}
