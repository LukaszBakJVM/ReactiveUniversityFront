package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.auth.AuthSession;
import org.example.reactiveuniversityfront.register.RegisterService;
import org.example.reactiveuniversityfront.register.dto.RegistrationRequestDto;
import org.example.reactiveuniversityfront.register.dto.RegistrationResponseDto;

@Route("registration")
public class RegisterView extends VerticalLayout implements BeforeEnterObserver {
    private final TextField firstName;
    private final TextField lastName;
    private final EmailField email;
    private final PasswordField password;
    private final ComboBox<String> role;
    private final RegisterService registerService;
    private final AuthSession authSession;


    public RegisterView(RegisterService registerService, AuthSession authSession) {
        this.registerService = registerService;
        this.authSession = authSession;


        role = new ComboBox<>();


        Button save = new Button("Zapisz", new Icon(VaadinIcon.SAFE), clickEvent -> registerNewPerson());
        firstName = new TextField("Imie");
        firstName.setPlaceholder("Imie");
        firstName.setRequired(true);

        lastName = new TextField("Nazwisko");
        lastName.setPlaceholder("Nazwisko");
        lastName.setRequired(true);


        email = new EmailField("email");
        email.setPlaceholder("email");
        email.setRequired(true);
        email.getElement().setAttribute("autocomplete", "new-email");
        email.getEmptyValue();

        password = new PasswordField("hasło");
        password.setPlaceholder("hasło");
        password.getElement().setAttribute("autocomplete", "new-password");


        add(firstName, lastName, email, password, role, save);
    }

    private void registerNewPerson() {
        try {


            RegistrationRequestDto request = new RegistrationRequestDto(firstName.getValue(), lastName.getValue(), email.getValue(), password.getValue(), role.getValue());
            RegistrationResponseDto newUser = registerService.createNewUser(request, authSession.getToken());
            Notification show = Notification.show("Zapisano" + newUser, 5000, Notification.Position.MIDDLE);
            show.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        } catch (Exception e) {
            Notification.show(e.getMessage(), 5000, Notification.Position.MIDDLE);
        }


    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {


        if (authSession.getToken().equals("notLogged")) {
            beforeEnterEvent.rerouteTo(LoginView.class);
        } else {
            role.setItems(registerService.roles());
            role.setValue("Office");
        }

    }
}
