package ru.innopolis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.List;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;


    @Bean
    public JdbcUserDetailsManager user(PasswordEncoder passwordEncoder) {
        var user = new JdbcUserDetailsManager(dataSource);

        if(!user.userExists("user")) {
            user.createUser(
                    new User(
                            "user",
                            passwordEncoder.encode("PasswordUser"),
                            List.of(
                                    new SimpleGrantedAuthority("USER")
                            )
                    )
            );
        }

        if(!user.userExists("admin")) {
            user.createUser(
                    new User(
                            "admin",
                            passwordEncoder.encode("PasswordAdmin"),
                            List.of(
                                    new SimpleGrantedAuthority("ADMIN")
                            )
                    )
            );
        }

        return user;
    }

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.POST, "/api/v1/bus_station/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/bus_station/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/bus_station/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/bus_station/user/list").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/bus_station/user/{username}").hasVariable("username").equalTo(Authentication::getName)
                                .requestMatchers("/api/v1/bus_station/**").permitAll()
                                .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 4);
    }

}
