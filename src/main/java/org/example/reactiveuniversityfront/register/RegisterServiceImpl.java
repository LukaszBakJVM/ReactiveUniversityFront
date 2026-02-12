package org.example.reactiveuniversityfront.register;

import org.example.reactiveuniversityfront.auth.AuthSession;
import org.example.reactiveuniversityfront.exception.RegistrationException;
import org.example.reactiveuniversityfront.register.dto.RegistrationRequestDto;
import org.example.reactiveuniversityfront.register.dto.RegistrationResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final RestClient restClient;
    private final AuthSession authSession;

    public RegisterServiceImpl(@Qualifier("reactiveUrl") RestClient restClient, AuthSession authSession) {
        this.restClient = restClient;
        this.authSession = authSession;
    }

    @Override
    public RegistrationResponseDto createNewUser(RegistrationRequestDto dto) {
        return restClient.post().uri("/user/registration").contentType(APPLICATION_JSON).body(dto).retrieve().onStatus(HttpStatusCode::is2xxSuccessful, (request, response) -> {
            if (response.getStatusCode().value() != 201) {
                throw new RegistrationException("Blad serwera");

            }
        }).body(RegistrationResponseDto.class);

    }

    @Override
    public List<String> roles() {
        return restClient.get().uri("/user/role").header("Authorization", "Bearer " + token()).accept(APPLICATION_JSON).retrieve().body(new ParameterizedTypeReference<>() {});


    }

    private String token() {
        return authSession.getToken();
    }


}
