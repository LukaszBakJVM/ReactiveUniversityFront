package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.auth.AuthService;
import org.example.reactiveuniversityfront.auth.AuthSession;
import org.example.reactiveuniversityfront.auth.dto.AuthRequest;
import org.example.reactiveuniversityfront.auth.dto.AuthResponse;
import org.example.reactiveuniversityfront.exception.BadCredentialsExceptions;

@Route("login")
public class LoginView extends VerticalLayout {

    private final EmailField emailField;
    private final PasswordField passwordField;

    private final AuthService authService;
    private final AuthSession authSession;

    public LoginView(AuthService authService, AuthSession authSession) {
        this.authService = authService;
        this.authSession = authSession;
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H1 header = new H1("📝 Logowanie");
        emailField = new EmailField("Email");
        emailField.setRequired(true);
        passwordField = new PasswordField("hasło");
        passwordField.setRequired(true);







        Button loginButton = new Button("zaloguj", event -> login());
        Button index = new Button("Powrót na strone główną",e -> UI.getCurrent().navigate(IndexView.class));
        add(header, emailField, passwordField, loginButton,index);
    }

    private void login() {


        try {


            AuthResponse authorization = authService.authorization(new AuthRequest(emailField.getValue(), passwordField.getValue()));
            authSession.saveToken(authorization.token());
            UI.getCurrent().navigate("");

        } catch (BadCredentialsExceptions ex) {
            Notification.show(ex.getMessage(), 1000, Notification.Position.MIDDLE);


        }


    }
}
