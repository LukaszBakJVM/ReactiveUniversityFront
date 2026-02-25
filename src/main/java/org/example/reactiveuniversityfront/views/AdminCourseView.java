package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.auth.AuthSession;
import org.example.reactiveuniversityfront.course.AdminCourseService;
import org.example.reactiveuniversityfront.course.CourseService;
import org.example.reactiveuniversityfront.course.dto.CourseDto;

import java.util.ArrayList;
import java.util.List;

@Route("course")
public class AdminCourseView extends VerticalLayout {


    private final Grid<CourseDto> course = new Grid<>();
    private final TextField subjectField = new TextField("Przedmiot");
    private final TextField courseField = new TextField();
    private final List<String> subjects = new ArrayList<>();
    private final Grid<String> subjectsGrid = new Grid<>(String.class, false);
    private final AuthSession authSession;
    private final CourseService courseService;
    private final AdminCourseService adminCourseService;
    private GridListDataView<String> dataView;

    public AdminCourseView(AuthSession authSession, CourseService courseService, AdminCourseService adminCourseService) {
        this.authSession = authSession;
        this.courseService = courseService;
        this.adminCourseService = adminCourseService;
        dataView = subjectsGrid.setItems(subjects);
        subjectsGrid.addColumn(item -> {
            int index = new ArrayList<>(dataView.getItems().toList()).indexOf(item) + 1;
            return String.valueOf(index);
        }).setHeader("Lp");


        Button addItemButton = new Button("Dodaj pozycję", p -> addSubject());
        Button save = new Button("Zapisz", e -> saveCourse());
        Button delete = new Button("Usuń", e -> deleteCourse());

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        HorizontalLayout courses = new HorizontalLayout(addItemButton, courseField, subjectField);
        add(courses, buttons, subjectsGrid);
    }

    private void addSubject() {

        String value = subjectField.getValue();
        subjects.add(value);
        subjectsGrid.setItems(subjects);
        subjectsGrid.getDataProvider().refreshAll();
        //  subjectField.clear();


    }

    private void saveCourse() {
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
