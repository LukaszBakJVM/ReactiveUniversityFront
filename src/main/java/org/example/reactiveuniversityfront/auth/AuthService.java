package org.example.reactiveuniversityfront.auth;

import org.example.reactiveuniversityfront.auth.dto.AuthRequest;
import org.example.reactiveuniversityfront.auth.dto.AuthResponse;

public interface AuthService {
    AuthResponse authorization(AuthRequest request);
}
