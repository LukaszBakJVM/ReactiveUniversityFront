package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.course.CourseService;
import org.example.reactiveuniversityfront.course.dto.CourseDto;
import org.example.reactiveuniversityfront.course.dto.Courses;

@Route("information")
public class CourseView extends VerticalLayout {

    private final CourseService courseService;
    private final Grid<CourseDto> course = new Grid<>();
    private final TextField subjectField = new TextField();

    public CourseView(CourseService courseService) {
        this.courseService = courseService;

        course.addColumn(CourseDto::courseName).setHeader("Kurs");
        course.addComponentColumn(courseDto -> {
            VerticalLayout layout = new VerticalLayout();
            for (String subject : courseDto.subjects()) {
                Button button = new Button(subject);
                button.addClickListener(e -> subjectField.setValue(subject));
                layout.add(button);
            }
            return layout;
        }).setHeader("Przedmioty");
        course.setSizeFull();



        subjectField.setPlaceholder("Przedmiot");
        subjectField.setReadOnly(true);

        Button searchAll = new Button("Wyszukaj kursy", event -> findCourse());
        Button searchBySubject = new Button("Wyszukaj kursy na podstawie przedmiotu", event -> findCourseBySubject());

        add(searchAll, searchBySubject, subjectField, course);
    }

    private void findCourseBySubject() {
        Courses courses = courseService.findCursesBySubject(subjectField.getValue());
        course.setItems(courses.courseDtos());
    }

    private void findCourse() {
        Courses courses = courseService.allCourse();
        course.setItems(courses.courseDtos());
    }
}
