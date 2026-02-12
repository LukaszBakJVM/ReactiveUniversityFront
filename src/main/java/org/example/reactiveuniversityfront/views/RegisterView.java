package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.register.RegisterService;

@Route("registration")
public class RegisterView extends VerticalLayout {
    private final ComboBox<String> role;
    private final RegisterService registerService;

    public RegisterView(RegisterService registerService) {
        this.registerService = registerService;
        role = new ComboBox<>();
        role.setItems(registerService.roles());
        role.setValue("Office");

        add(role);
    }
}
