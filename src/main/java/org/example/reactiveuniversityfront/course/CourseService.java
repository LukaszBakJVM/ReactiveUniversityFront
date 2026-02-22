package org.example.reactiveuniversityfront.course;

import org.example.reactiveuniversityfront.course.dto.Courses;

public interface CourseService {
    Courses allCourse();
    Courses findCursesBySubject(String subject);
}
