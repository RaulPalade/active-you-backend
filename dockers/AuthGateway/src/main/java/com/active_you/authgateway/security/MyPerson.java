package com.active_you.authgateway.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class MyPerson extends User {
    private Long id;
    private String name;
    private String email;

    public MyPerson(Long id, String name, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
