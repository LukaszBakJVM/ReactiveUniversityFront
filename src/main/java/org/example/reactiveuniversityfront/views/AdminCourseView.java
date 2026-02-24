package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.example.reactiveuniversityfront.auth.AuthSession;
import org.example.reactiveuniversityfront.course.AdminCourseService;
import org.example.reactiveuniversityfront.course.CourseService;

public class AdminCourseView extends VerticalLayout {
private final AuthSession authSession;
private final CourseService courseService;
private final AdminCourseService adminCourseService;

    public AdminCourseView(AuthSession authSession, CourseService courseService, AdminCourseService adminCourseService) {
        this.authSession = authSession;
        this.courseService = courseService;
        this.adminCourseService = adminCourseService;
    }

}
