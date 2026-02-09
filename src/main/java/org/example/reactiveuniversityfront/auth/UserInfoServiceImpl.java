package org.example.reactiveuniversityfront.auth;

import org.example.reactiveuniversityfront.auth.dto.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Service
public class UserInfoServiceImpl  implements  UserInfoService {
    @Value("${reactiveUrl}")
    private String reactiveUrl;
    private final RestClient restClient;

    public UserInfoServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }


    @Override
    public UserInfo getCurrentUser() {
     return    restClient.get().uri(url(getTokenFromRequest())).retrieve().body(UserInfo.class);

    }

    private String url(final String token) {
        return fromUriString(reactiveUrl).path("/user/userInfo/{token}").buildAndExpand(token).toUriString();

    }

    private String getTokenFromRequest() {
        // Przykład pobrania tokena z ciasteczka Vaadin
        return com.vaadin.flow.server.VaadinService.getCurrentRequest()
                .getWrappedSession()
                .getAttribute("authToken") != null ?
                (String) com.vaadin.flow.server.VaadinService.getCurrentRequest()
                        .getWrappedSession()
                        .getAttribute("authToken")
                : null;
    }
}


