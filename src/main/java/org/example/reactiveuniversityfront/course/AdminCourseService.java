package org.example.reactiveuniversityfront.course;

import org.example.reactiveuniversityfront.course.dto.CourseDto;

public interface AdminCourseService {
    CourseDto createNewCourse(CourseDto request, String bearer);
    void deleteCourse(String courseName, String bearer);
}
