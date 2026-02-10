package org.example.reactiveuniversityfront.auth;

public interface AuthSession {
    void saveToken(String token);
    String getToken();
    void clear();
}
