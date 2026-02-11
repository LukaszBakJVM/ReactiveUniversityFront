package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("admin")
public class AdminView extends VerticalLayout {
    public AdminView() {
        RouterLink link = new RouterLink("Zarejstruj się", RegisterView.class);

        Button registration = new Button("Zarejstruj się", e -> UI.getCurrent().navigate(RegisterView.class));
        add(registration, link);


    }
}
