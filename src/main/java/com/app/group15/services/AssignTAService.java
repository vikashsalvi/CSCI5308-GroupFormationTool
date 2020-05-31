package com.app.group15.services;

import com.app.group15.persistence.dao.CourseDao;
import com.app.group15.persistence.dao.CourseInstructorMapperDao;
import com.app.group15.persistence.dao.UserDao;
import com.app.group15.persistence.dao.UserRoleDao;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.entity.UserRoleMapperEntity;
import com.app.group15.persistence.injectors.CourseDaoInjectorService;
import com.app.group15.persistence.injectors.CourseInstructorMapperDaoInjectorService;
import com.app.group15.persistence.injectors.UserDaoInjectorService;
import com.app.group15.persistence.injectors.UserRoleDaoInjectorService;

import java.util.Set;

public class AssignTAService {

    private UserDao userDao = new UserDaoInjectorService().getUserDao();
    private CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();
    private UserRoleDao userRoleDao = new UserRoleDaoInjectorService().getUserRoleDao();
    private CourseInstructorMapperDao courseInstructorMapperDao = new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();


    public boolean performTAUpdate(String bannerId, String courseId)
    {
        if (validateBannerID(bannerId) && validateCourseID(courseId)) {
            //TA info
            UserEntity userEntity= userDao.getUserByBannerId(bannerId);
            courseInstructorMapperDao.addTaToACourse(Integer.parseInt(courseId),userEntity.getId());
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

    public boolean validateCourseID(String courseId) {
        if (courseDao.get(Integer.parseInt(courseId)) != null)
        {
            return true;
        }else {
            return false;
        }
    }

//    public boolean validateTAisNotTakingSameSubject(String bannerId)
//    {
//        return
//    }



}
