package org.example.reactiveuniversityfront.register;

import org.example.reactiveuniversityfront.exception.RegistrationException;
import org.example.reactiveuniversityfront.register.dto.RegistrationRequestDto;
import org.example.reactiveuniversityfront.register.dto.RegistrationResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final RestClient restClient;

    public RegisterServiceImpl(@Qualifier("reactiveUrl") RestClient restClient) {
        this.restClient = restClient;
    }

    RegistrationResponseDto createNewUser(RegistrationRequestDto dto) {
        return restClient.post().uri("/user//registration").contentType(MediaType.APPLICATION_JSON).body(dto).retrieve().onStatus(HttpStatusCode::is2xxSuccessful, (request, response) -> {
            if (response.getStatusCode().value() != 201) {
                throw new RegistrationException("Blad serwera");

            }
        }).body(RegistrationResponseDto.class);

    }


}
