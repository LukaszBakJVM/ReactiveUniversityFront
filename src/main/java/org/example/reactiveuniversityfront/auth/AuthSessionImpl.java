package org.example.reactiveuniversityfront.auth;

import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Component;

@Component
public class AuthSessionImpl implements AuthSession {
    private final String TOKEN = "Bearer";

    @Override
    public void saveToken(String token) {
        VaadinSession.getCurrent().setAttribute(TOKEN, token);
    }

    @Override
    public String getToken() {
        String attribute = (String) VaadinSession.getCurrent().getAttribute(TOKEN);

        if (attribute == null) {
            return "notLogged";
        }
        return attribute;
    }

    @Override
    public void clear() {
        VaadinSession.getCurrent().setAttribute(TOKEN, null);
    }
}

