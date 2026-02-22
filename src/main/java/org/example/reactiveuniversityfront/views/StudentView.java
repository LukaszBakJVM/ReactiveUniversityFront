package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.auth.AuthSession;

@Route("student")
public class StudentView extends VerticalLayout implements BeforeEnterObserver {
    private final AuthSession authSession;

    public StudentView(AuthSession authSession) {
        this.authSession = authSession;
        H1 title = new H1("Student");
        add(title);
    }

    @Override
        public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {


            if (authSession.getToken().equals("notLogged")) {
                beforeEnterEvent.rerouteTo(LoginView.class);


        }

    }
}
