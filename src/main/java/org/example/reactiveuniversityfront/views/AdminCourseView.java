package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.auth.AuthSession;
import org.example.reactiveuniversityfront.course.AdminCourseService;
import org.example.reactiveuniversityfront.course.CourseService;
import org.example.reactiveuniversityfront.course.dto.CourseDto;
import org.example.reactiveuniversityfront.exception.ConflictException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Route("course")
public class AdminCourseView extends VerticalLayout  implements BeforeEnterObserver {


    private final Grid<CourseDto> course = new Grid<>();
    private final TextField subjectField = new TextField("Przedmiot");
    private final TextField courseField = new TextField();
    private final List<String> subjects = new ArrayList<>();
    private final Grid<String> subjectsGrid = new Grid<>(String.class, false);
    private final AuthSession authSession;
    private final CourseService courseService;
    private final AdminCourseService adminCourseService;


    public AdminCourseView(AuthSession authSession, CourseService courseService, AdminCourseService adminCourseService) {
        this.authSession = authSession;
        this.courseService = courseService;
        this.adminCourseService = adminCourseService;

        subjectsGrid.setItems(subjects);

        subjectsGrid.addColumn(item -> subjects.indexOf(item) + 1).setHeader("Lp");

        subjectsGrid.addColumn(item -> item).setHeader("Przedmiot");


        Button addItemButton = new Button("Dodaj pozycję", p -> addSubject());
        addItemButton.setHeight("150");
        Button save = new Button("Zapisz", e -> saveCourse());
        Button delete = new Button("Usuń", e -> deleteCourse());

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        HorizontalLayout courses = new HorizontalLayout(addItemButton, courseField, subjectField);
        add(courses, buttons, subjectsGrid);
    }

    private void addSubject() {
        try {


            String value = subjectField.getValue();
            if (subjects.contains(value)) {

                throw new ConflictException("Przediot już jest na liście");
            }
            subjects.add(value);
            subjectsGrid.setItems(subjects);
        } catch (ConflictException e) {
            Notification.show(e.getMessage(), 1000, Notification.Position.MIDDLE);
        }


    }

    private void saveCourse() {
        try {
            String token = authSession.getToken();

            adminCourseService.createNewCourse(new CourseDto(courseField.getValue(), new HashSet<>(subjects)), token);
        } catch (RuntimeException e) {
            Notification.show(e.getMessage(), 1000, Notification.Position.MIDDLE);
        }
    }

    private void deleteCourse() {
        try {


            String token = authSession.getToken();


            adminCourseService.deleteCourse(courseField.getValue(), token);

        } catch (Exception e) {
            Notification.show(e.getMessage(), 2000, Notification.Position.MIDDLE);
        }

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (authSession.getToken().equals("notLogged")) {
            beforeEnterEvent.rerouteTo(LoginView.class);

        }
    }
}
