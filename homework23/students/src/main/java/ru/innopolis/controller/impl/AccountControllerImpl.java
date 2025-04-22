package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.AccountController;
import ru.innopolis.dto.account.AccountResponse;
import ru.innopolis.service.AccountService;


@RestController
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;


    @Override
    public ResponseEntity<AccountResponse> getCoursesForAccount(UserDetails userDetails) {
        AccountResponse accountResponse = accountService.findOneStudentAllCourses(userDetails.getUsername());

        if (accountResponse != null) {
            return ResponseEntity.ok().body(accountResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
