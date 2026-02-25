package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.auth.AuthSession;
import org.example.reactiveuniversityfront.course.AdminCourseService;
import org.example.reactiveuniversityfront.course.CourseService;
import org.example.reactiveuniversityfront.course.dto.CourseDto;

@Route("course")
public class AdminCourseView extends VerticalLayout {


    private final Grid<CourseDto> course = new Grid<>();
    private final TextField courseField = new TextField();

    private final AuthSession authSession;
    private final CourseService courseService;
    private final AdminCourseService adminCourseService;

    public AdminCourseView(AuthSession authSession, CourseService courseService, AdminCourseService adminCourseService) {
        this.authSession = authSession;
        this.courseService = courseService;
        this.adminCourseService = adminCourseService;

        Button delete = new Button(new Icon(VaadinIcon.DEL), e -> deleteCourse());
        add(courseField, delete);
    }

    private void deleteCourse() {
        try {


            String token = authSession.getToken();


            adminCourseService.deleteCourse(courseField.getValue(), token);

        } catch (Exception e) {
            Notification.show(e.getMessage(), 2000, Notification.Position.MIDDLE);
        }

    }
}
