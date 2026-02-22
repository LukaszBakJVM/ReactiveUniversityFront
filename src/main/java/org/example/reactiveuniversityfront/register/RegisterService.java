package org.example.reactiveuniversityfront.register;

import org.example.reactiveuniversityfront.register.dto.RegistrationRequestDto;
import org.example.reactiveuniversityfront.register.dto.RegistrationResponseDto;

import java.util.List;

public interface RegisterService {
    RegistrationResponseDto createNewUser(RegistrationRequestDto dto,String bearer);
    List<String> roles();
}
