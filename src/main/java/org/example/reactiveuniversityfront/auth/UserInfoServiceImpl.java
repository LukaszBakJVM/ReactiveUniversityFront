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
    private final AuthSession authSession;

    public UserInfoServiceImpl(RestClient restClient, AuthSession authSession) {
        this.restClient = restClient;
        this.authSession = authSession;
    }


    @Override
    public UserInfo getCurrentUser() {
        return restClient.get().uri(url(getTokenFromRequest())).retrieve().body(UserInfo.class);


    }

    private String url(final String token) {
        return fromUriString(reactiveUrl).path("/user/userInfo/{token}").buildAndExpand(token).toUriString();

    }

    private String getTokenFromRequest() {
       return authSession.getToken();

    }
}

