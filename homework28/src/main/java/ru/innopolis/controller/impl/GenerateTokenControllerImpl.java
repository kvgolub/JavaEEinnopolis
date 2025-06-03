package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.GenerateTokenController;
import ru.innopolis.utility.JwtUtil;


@RestController
@RequiredArgsConstructor
public class GenerateTokenControllerImpl implements GenerateTokenController {

    @Override
    public ResponseEntity<String> generateJwt(String username) {
        String token = new JwtUtil().generateToken(username);

        return ResponseEntity.ok().body(token);
    }
}
