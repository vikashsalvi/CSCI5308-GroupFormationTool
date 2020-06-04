package com.app.group15.services;

import com.app.group15.model.Course;
import com.app.group15.model.CourseInstructorMapper;
import com.app.group15.persistence.dao.CourseDaoMock;
import com.app.group15.persistence.dao.CourseInstructorMapperDaoMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CourseServiceTest {

    CourseDaoMock courseDaoMock = new CourseDaoMock();
    CourseInstructorMapperDaoMock courseInstructorMapperDaoMock = new CourseInstructorMapperDaoMock();

    @Test
    public void getCoursesListTest() {
        ArrayList<Course> allCourses = courseDaoMock.getAllCourses();
        assertEquals(allCourses.get(0).getId(), 1);
        assertEquals(allCourses.get(1).getId(), 2);
    }

    @Test
    public void getCourseDetailsTest() {
        Course courseByCourseIdMock = courseDaoMock.getCourseByCourseIdMock("6");
        assertEquals(courseByCourseIdMock.getName(), "CSCI5409");
    }

    @Test
    public void getCourseInstructor() {
        CourseInstructorMapper courseInstructorMapper = courseInstructorMapperDaoMock.getCourseInstructorMapperEntity(6);
        assertEquals(courseInstructorMapper.getInstructorId(), 17);
    }

    @Test
    public void getAllCourseInstructorsTest() {
        ArrayList<Course> courses = new ArrayList<>();
        Course c1 = new Course();
        c1.setName("CSCI5409");
        c1.setId(6);
        courses.add(c1);
        CourseInstructorMapper courseInstructorMapper = courseInstructorMapperDaoMock.getCourseInstructorMapperEntity(courses.get(0).getId());
        assertEquals(courseInstructorMapper.getInstructorId(), 17);
    }

    @Test
    public void addOrUpdateInstructorTest() {
        int instructorId = 1;
        CourseInstructorMapperDaoMock courseInstructorMapperDaoMock = new CourseInstructorMapperDaoMock();
        CourseInstructorMapper courseInstructorMapper = courseInstructorMapperDaoMock.addOrUpdateInstructor(instructorId);
        assertEquals(1, courseInstructorMapper.getInstructorId());
    }


    @Test
    public void addCourse() {
        CourseDaoMock courseDaoMock = new CourseDaoMock();
        Course course = courseDaoMock.addCourse("CSCI5409");
        assertEquals(course.getId(), 10);
        assertNotNull(course);
    }

    @Test
    public void deleteCourse() {
        CourseDaoMock courseDaoMock = new CourseDaoMock();
        Course course = courseDaoMock.addCourse("CSCI5409");
        assertEquals(course.getId(), 10);
        Course deletdCourse = courseDaoMock.deleteCourse(course);
        assertNull(deletdCourse);

    }

}
