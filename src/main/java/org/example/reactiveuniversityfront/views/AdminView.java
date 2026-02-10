package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("admin")
public class AdminView extends VerticalLayout {
    public AdminView() {

        add(new H1("admin"));
        add(new Paragraph("This is the home view"));

        add(new Paragraph("You can edit this view in src\\main\\java\\org\\example\\reactiveuniversityfront\\views\\HomeView.java"));

    }
}
