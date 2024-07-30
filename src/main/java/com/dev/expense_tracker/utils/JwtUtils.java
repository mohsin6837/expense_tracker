package com.dev.expense_tracker.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.dev.expense_tracker.constant.LoggingConstants.*;

@Slf4j
@Component
public class JwtUtils {

    private static final String ISSUER = "EXPENSE_TRACKER_AUTH_SERVICE";
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    public static String generateAccessToken(String email) {
        var methodName = "JwtUtils:generateAccessToken";
        log.info(START_METHOD_LOGGER, methodName, email);
        var issuedAt = new Date();
        var expiredAt = DateUtils.addMinutes(issuedAt, 20);
        String accessToken = Jwts.builder()
                .issuer(ISSUER)
                .subject(email)
                .id(UUID.randomUUID().toString())
                .issuedAt(issuedAt)
                .expiration(expiredAt)
                .signWith(SECRET_KEY)
                .compact();
        log.info(END_METHOD_LOGGER, methodName);
        return accessToken;
    }

    public static Optional<String> getUsernameFromToken(String accessToken) {
        return parseToken(accessToken).map(Claims::getSubject);
    }

    private static Optional<Claims> parseToken(String accessToken) {
        var methodName = "JwtUtils:parseToken";
        var parser = Jwts.parser().verifyWith(SECRET_KEY).build();
        try {
            return Optional.ofNullable(parser.parseSignedClaims(accessToken).getPayload());
        }
        catch (JwtException | IllegalArgumentException e) {
            log.error(ERROR_METHOD_LOGGER, methodName, "Error while parsing token.");
            return Optional.empty();
        }
    }
}
