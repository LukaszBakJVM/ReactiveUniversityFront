package org.example.reactiveuniversityfront.course;

import org.example.reactiveuniversityfront.course.dto.Courses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CourseServiceImpl implements CourseService {
    private final RestClient courseUrl;

    public CourseServiceImpl(@Qualifier("courseUrl") RestClient courseUrl) {
        this.courseUrl = courseUrl;
    }

    @Override
    public Courses allCourse() {
        return courseUrl.get().uri("/course/all").accept(MediaType.APPLICATION_JSON).retrieve().body(Courses.class);
    }

    @Override
    public Courses findCursesBySubject(String subject) {
        return courseUrl.get().uri(uriBuilder -> uriBuilder.path("course/{subject}/name").build(subject))
                .accept(MediaType.APPLICATION_JSON).retrieve().body(Courses.class);
    }
}
