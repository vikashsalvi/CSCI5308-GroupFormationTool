package com.app.group15.persistence.dao;

import com.app.group15.persistence.entity.CourseInstructorMapperEntity;

public class CourseInstructorMapperDaoMock {

    CourseInstructorMapperEntity courseInstructorMapperEntity = new CourseInstructorMapperEntity();

    public CourseInstructorMapperEntity getCourseInstructorMapperEntity(int courseId)
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
