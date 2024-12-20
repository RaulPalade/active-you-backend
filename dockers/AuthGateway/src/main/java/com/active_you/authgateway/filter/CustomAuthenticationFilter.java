package com.active_you.authgateway.filter;

import com.active_you.authgateway.repository.PersonRepository;
import com.active_you.authgateway.security.MyPerson;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email);
        System.out.println(password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws AuthenticationException, IOException {
        MyPerson person = (MyPerson) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("chiaveSegreta".getBytes());
        String accessToken = JWT.create()
                .withSubject(person.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 240 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", person.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withJWTId(person.getId().toString())
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();

        tokens.put("userId", String.valueOf(person.getId()));
        tokens.put("access_token", accessToken);
        tokens.put("name", person.getName());
        if (person.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).contains("TRAINER")) {
            tokens.put("trainer", "true");
        }
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
