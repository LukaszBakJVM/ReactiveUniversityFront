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
    private final Grid<CourseDto> course;
    private  final TextField subjectField;

    public CourseView(CourseService courseService) {
        this.courseService = courseService;
        course = new Grid<>();
        course.addColumn(CourseDto::courseName).setHeader("Kurs");
        course.addColumn(CourseDto::subjects).setHeader("Przedmioty");
        subjectField = new TextField();
        subjectField.setPlaceholder("Przedmiot");
        subjectField.setRequired(true);
        Button course = new Button("Wyszukaj kursy", event -> findCourse());
        Button subject = new Button("Wyszukaj kursy na podstawie przdmiotu ",event->findCourseBySubject()) ;
        add(course,subject,subjectField);
    }

    private void findCourseBySubject() {
        course.setItems();

        Courses courses = courseService.findCursesBySubject(subjectField.getValue());
        course.setItems(courses.courseDtos());


    }

    private void findCourse() {
        course.setItems();
        Courses courses = courseService.allCourse();
        course.setItems(courses.courseDtos());
        add(course);

    }

}
