package com.app.group15.services;



import com.app.group15.dao.CourseDao;
import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.dao.UserDao;
import com.app.group15.dao.UserRoleDao;
import com.app.group15.injectors.CourseDaoInjectorService;
import com.app.group15.injectors.CourseInstructorMapperDaoInjectorService;
import com.app.group15.injectors.UserDaoInjectorService;
import com.app.group15.injectors.UserRoleDaoInjectorService;
import com.app.group15.model.Course;
import com.app.group15.model.User;

import java.util.ArrayList;
import java.util.Set;

public class AssignTAService {

    private UserDao userDao = new UserDaoInjectorService().getUserDao();
    private CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();
    private UserRoleDao userRoleDao = new UserRoleDaoInjectorService().getUserRoleDao();
    private CourseInstructorMapperDao courseInstructorMapperDao = new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();


    public boolean performTAUpdate(String bannerId, int courseId)
    {
        User userEntity= userDao.getUserByBannerId(bannerId);

        if (validateBannerID(bannerId) && validateCourseID(courseId)) {
            System.out.println("HERE");
            courseInstructorMapperDao.addTaToACourse(courseId,userEntity.getId());
            return true;
        }else {
            return false;
        }
    }

    public  boolean validateBannerID(String bannerId) {

        Set roleSet = userRoleDao.getRolesByBannerId(bannerId);
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

        boolean returnVar =false;
        ArrayList<Course> courseEntitiesList = courseInstructorMapperDao.getCourseByInstructor(instructorId);

          for (int i=0; i < courseEntitiesList.size(); i++) {
              Course courseEntity = courseEntitiesList.get(i);
              if (courseEntity.getId() == courseId) {
                  returnVar = true;
                  break;
              }
          }
          return returnVar;
    }

}
