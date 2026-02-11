package org.example.reactiveuniversityfront.auth;

import org.example.reactiveuniversityfront.auth.dto.AuthRequest;
import org.example.reactiveuniversityfront.auth.dto.AuthResponse;
import org.example.reactiveuniversityfront.exception.BadCredentialsExceptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Qualifier("AuthServiceImpl")
public class AuthServiceImpl implements AuthService {


    private final RestClient restClient;

    public AuthServiceImpl(@Qualifier("reactiveUrl") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public AuthResponse authorization(AuthRequest request) {
        return restClient.post().uri("/login").body(request).contentType(MediaType.APPLICATION_JSON).retrieve().onStatus(HttpStatusCode::is4xxClientError, (request1, response) -> {
            throw new BadCredentialsExceptions("Bad Credentials");
        }).body(AuthResponse.class);
    }


}


