package ru.innopolis.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Slf4j
@RestControllerAdvice
public class CustomFilter implements jakarta.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Вывод информации в лог (консоль) о каждом входящем запросе (метод, URI, заголовок Authorization - в декодированном виде)
        var base64Encoder = Base64.getDecoder();
        String authHeader = request.getHeader("Authorization").replace("Basic ", "");
        var decodedHeader =  new String(base64Encoder.decode(authHeader.getBytes(StandardCharsets.UTF_8)));

        log.info("Input request: method - '{}', URI - '{}', authorization header - '{}'",
                request.getMethod(),
                request.getRequestURI(),
                decodedHeader
        );

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
