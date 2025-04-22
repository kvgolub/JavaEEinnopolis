package ru.innopolis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.dto.account.AccountResponse;


@RequestMapping("/api/v1/account")
public interface AccountController {

    @GetMapping("/personal")
    ResponseEntity<AccountResponse> getCoursesForAccount(@AuthenticationPrincipal UserDetails userDetails);

}
