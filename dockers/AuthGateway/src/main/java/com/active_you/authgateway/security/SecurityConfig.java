package com.active_you.authgateway.security;

import com.active_you.authgateway.filter.CustomAuthenticationFilter;
import com.active_you.authgateway.filter.CustomAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests()
                .antMatchers(GET, "api/v1/auth").permitAll()
                .antMatchers("api/v1/login").permitAll()
                .antMatchers(GET, "/userService/api/v1/users").permitAll()
                .antMatchers(GET, "/userService/api/v1/users/{id}").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/userService/api/v1/users/").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(DELETE, "/userService/api/v1/users/{id}").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(GET, "/userService/api/v1/users/{id}/goals").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/userService/api/v1/users/{id}/goals").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(DELETE, "/userService/api/v1/users/{id}/goals/{goalId}").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/userService/api/v1/users/{id}/goals/{goalId}").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/userService/api/v1/users/createWorkout").hasAnyAuthority("TRAINER")
                .antMatchers(POST, "/userService/api/v1/users/createExercise").hasAnyAuthority("TRAINER")
                .antMatchers(POST, "/userService/api/v1/users/createExercise").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/userService/api/v1/personFollow/follow").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/userService/api/v1/personFollow/unfollow").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/userService/api/v1/personFollow/unfollow").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(GET, "/workoutService/api/v1/workouts").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(GET, "/workoutService/api/v1/workouts/{id}").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/workoutService/api/v1/workouts").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(DELETE, "/workoutService/api/v1/workouts").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/workoutService/api/v1/workouts/{id}/exercises").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(GET, "/workoutService/api/v1/exercises/workouts/{id}").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(GET, "/workoutService/api/v1/exercises").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(GET, "/workoutService/api/v1/exercises/{id}").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(GET, "/workoutService/api/v1/personWorkouts").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/workoutService/api/v1/personWorkouts").hasAnyAuthority("USER", "TRAINER")
                .antMatchers(POST, "/workoutService/api/v1/personWorkouts/{id}").hasAnyAuthority("USER", "TRAINER");
        http.authorizeRequests().anyRequest().denyAll();

        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
