package com.active_you.authgateway;

import com.active_you.authgateway.models.Role;
import com.active_you.authgateway.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class AuthGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthGatewayApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository) {
        return args -> {
            if (!roleRepository.existsByName("ADMIN")) {
                Role adminRole = new Role();
                adminRole.setName("ADMIN");
                roleRepository.save(adminRole);
            }

            if (!roleRepository.existsByName("USER")) {
                Role userRole = new Role();
                userRole.setName("USER");
                roleRepository.save(userRole);
            }
        };
    }
}
