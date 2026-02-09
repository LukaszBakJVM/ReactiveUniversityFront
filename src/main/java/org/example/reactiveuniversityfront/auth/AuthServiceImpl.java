package org.example.reactiveuniversityfront.auth;

import org.example.reactiveuniversityfront.auth.dto.AuthRequest;
import org.example.reactiveuniversityfront.auth.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${reactiveUrl}")
    private String reactiveUrl;

    private final RestClient restClient;

    public AuthServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public AuthResponse authorization(AuthRequest request) {
        return restClient.post().uri(url("/login")).body(request).contentType(MediaType.APPLICATION_JSON).retrieve().body(AuthResponse.class);
    }


    private String url(final String path) {
        return fromUriString(reactiveUrl).path(path).toUriString();

    }
}


