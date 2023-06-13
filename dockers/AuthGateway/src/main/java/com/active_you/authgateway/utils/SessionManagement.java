package com.active_you.authgateway.utils;

import com.active_you.authgateway.models.Person;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.*;

public class SessionManagement {
    public static Map<String, String> getJWTResponse(Person person) {
        Map<String, String> response = new HashMap<>();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("USER");
        Algorithm algorithm = Algorithm.HMAC256("chiaveSegreta".getBytes());
        String accessToken = JWT.create()
                .withSubject(person.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 240 * 60 * 1000))
                .withClaim("roles", roles)
                .withJWTId(person.getId().toString())
                .sign(algorithm);

        response.put("userId", String.valueOf(person.getId()));
        response.put("access_token", accessToken);
        response.put("name", person.getName());

        return response;
    }
}
