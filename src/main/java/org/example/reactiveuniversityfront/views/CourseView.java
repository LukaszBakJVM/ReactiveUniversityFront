package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.course.CourseService;
import org.example.reactiveuniversityfront.course.dto.CourseDto;
import org.example.reactiveuniversityfront.course.dto.Courses;

@Route("course")
public class CourseView extends VerticalLayout {
    private final CourseService courseService;
    private final Grid<CourseDto> course;

    public CourseView(CourseService courseService) {
        this.courseService = courseService;
        course = new Grid<>(CourseDto.class);
        course.addColumn(CourseDto::courseName).setHeader("Kurs");
        course.addColumn(CourseDto::subjects).setHeader("Przedmioty");
        Button button = new Button("Wyszukaj kursy", event -> findCourse());
        add(button);
    }

    private void findCourse() {


        course.setItems();
        Courses courses = courseService.allCourse();

        course.setItems(courses.courseDtos());
        add(course);

    }

}
