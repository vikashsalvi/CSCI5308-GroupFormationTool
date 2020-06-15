package com.app.group15.services;

import com.app.group15.courseManagement.Course;
import com.app.group15.persistence.dao.CourseInstructorMapperDaoMock;
import com.app.group15.userManagement.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InstructorServiceTest {
    CourseInstructorMapperDaoMock courseInstructorMapperDaoMock = new CourseInstructorMapperDaoMock();

    @Test
    public void getCourseOfInstructorTest() {
        ArrayList<Course> coursesOfInstructorMock = courseInstructorMapperDaoMock.getCoursesOfInstructorMock(10);
        assertEquals(coursesOfInstructorMock.get(0).getId(), 1);
        assertEquals(coursesOfInstructorMock.get(1).getId(), 2);
        assertNotEquals(coursesOfInstructorMock.get(1).getId(), 3);
    }

    @Test
    public void getCourseTATest() {
        int courseId = 10;
        User userEntity = courseInstructorMapperDaoMock.getCourseTAMock(courseId);
        assertEquals(userEntity.getId(), 1);
        assertNotNull(userEntity);
    }

    @Test
    public void getAllCourseTATest() {
        Course c1 = new Course();
        c1.setName("CSCI5401");
        c1.setId(1);
        Course c2 = new Course();
        c2.setName("CSCI5402");
        c2.setId(2);
        Course c3 = new Course();
        c3.setName("CSCI5403");
        c3.setId(3);
        ArrayList<Course> arrayList = new ArrayList<>();
        arrayList.add(c1);
        arrayList.add(c2);
        arrayList.add(c3);

        ArrayList<User> allCourseTa = courseInstructorMapperDaoMock.getAllCourseTa(arrayList);
        assertEquals(allCourseTa.get(0).getId(), 1);
        assertEquals(allCourseTa.get(1).getId(), 2);
        assertEquals(allCourseTa.get(2).getId(), 3);
        assertNotEquals(allCourseTa.get(1), 3);

    }
}
