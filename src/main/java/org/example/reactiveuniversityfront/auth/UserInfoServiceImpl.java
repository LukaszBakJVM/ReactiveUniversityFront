package org.example.reactiveuniversityfront.auth;

import org.example.reactiveuniversityfront.auth.dto.UserInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserInfoServiceImpl  implements  UserInfoService {


    private final RestClient restClient;
    private final AuthSession authSession;

    public UserInfoServiceImpl(@Qualifier("reactiveUrl") RestClient restClient, AuthSession authSession) {
        this.restClient = restClient;
        this.authSession = authSession;
    }


    @Override
    public UserInfo getCurrentUser() {
        return restClient.get().uri(uriBuilder -> uriBuilder.path("/user/userInfo/{token}").build(getTokenFromRequest())).retrieve().body(UserInfo.class);




    }


    private String getTokenFromRequest() {
       return authSession.getToken();

    }
}

