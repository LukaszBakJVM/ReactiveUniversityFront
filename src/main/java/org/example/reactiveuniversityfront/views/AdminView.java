package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("admin")
public class AdminView extends VerticalLayout {
    public AdminView() {


        Button registration = new Button("Zarejstruj osobę", e -> UI.getCurrent().navigate(RegisterView.class));
        Button course = new Button("Dodaj  nowy  kurs", e -> UI.getCurrent().navigate(AdminCourseView.class));
        add(registration, course);


    }
}
