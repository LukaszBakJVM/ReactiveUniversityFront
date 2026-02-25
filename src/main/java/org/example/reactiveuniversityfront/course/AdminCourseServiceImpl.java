package org.example.reactiveuniversityfront.course;

import org.example.reactiveuniversityfront.course.dto.CourseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AdminCourseServiceImpl implements AdminCourseService {
    private final RestClient course;

    public AdminCourseServiceImpl(@Qualifier("courseUrl") RestClient course) {
        this.course = course;

    }

  public   CourseDto createNewCourse(CourseDto request, String bearer) {
        return course.post().uri("/course").header(HttpHeaders.AUTHORIZATION, "Bearer " + bearer).body(request)
                .retrieve().body(CourseDto.class);
    }

   public void deleteCourse(String courseName, String bearer) {
        course.delete().uri(uriBuilder -> uriBuilder.path("/course/{courseName}").build(courseName))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearer).retrieve().body(Void.class);
    }

}
