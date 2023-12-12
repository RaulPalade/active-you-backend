package com.active_you.userservice;

import com.active_you.userservice.models.Role;
import com.active_you.userservice.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
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
