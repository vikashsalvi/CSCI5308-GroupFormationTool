package com.app.group15.persistence.dao;

import com.app.group15.model.CourseInstructorMapper;

public class CourseInstructorMapperDaoMock {

    CourseInstructorMapper courseInstructorMapperEntity = new CourseInstructorMapper();

    public CourseInstructorMapper getCourseInstructorMapperEntity(int courseId)
    {
        if (courseId == 6)
        {
            courseInstructorMapperEntity.setCourseId(6);
            courseInstructorMapperEntity.setInstructorId(17);
            courseInstructorMapperEntity.setTaId(0);
        }

        return courseInstructorMapperEntity;
    }

    public boolean setTA(int courseId)
    {
        if (courseId == 6)
        {
            courseInstructorMapperEntity.setTaId(11);
            return  true;
        }else {return  false;}
    }

}
