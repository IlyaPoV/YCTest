package ru.yescoding.app.service;

public interface TokenService {
    String verifyToken(String fullToken);

    String generateAccessToken(String userId);

    String generateRefreshToken(String userId);
}
