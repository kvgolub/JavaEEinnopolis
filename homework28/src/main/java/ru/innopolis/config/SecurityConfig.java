package ru.innopolis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.innopolis.filter.JwtRequestFilter;

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
//        var user = new InMemoryUserDetailsManager();

        if(!user.userExists("admin")) {
            user.createUser(
                    new User(
                            "admin",
                            passwordEncoder.encode("adminpass"),
                            List.of(
                                    new SimpleGrantedAuthority("ADMIN")
                            )
                    )
            );
        }

        return user;
    }

    @Bean
    public SecurityFilterChain generateTokenFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/get_token/**")
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.GET, "/api/get_token/{username}").authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/api/tasks/**")
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.POST, "/api/tasks").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/tasks/{id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/tasks").authenticated()
                )
                .addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 4);
    }
}
