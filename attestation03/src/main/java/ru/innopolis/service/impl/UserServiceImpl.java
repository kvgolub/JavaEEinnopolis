package ru.innopolis.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.user.UserResponse;
import ru.innopolis.entity.Authority;
import ru.innopolis.entity.User;
import ru.innopolis.repository.AuthorityRepository;
import ru.innopolis.repository.UserRepository;
import ru.innopolis.service.UserService;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }


    @Override
    public Optional<UserResponse> findByIdUser(String userName) {
        try {
            User user = userRepository.findById(userName).orElseThrow();

            List<Authority> authorities = authorityRepository.findAllById(List.of(userName));
            List<String> authorityList = new ArrayList<>();
            authorities.forEach(authority -> authorityList.add(authority.getAuthority()));

            return Optional.of(
                    new UserResponse(
                            user.getUsername(),
                            user.getPassword(),
                            user.getEnabled(),
                            authorityList
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<UserResponse> findAllUsers() {
        List<User> userList = userRepository.findAll();

        List<UserResponse> userResponseList = new ArrayList<>();
        userList.forEach(user -> {
                List<Authority> authorities = authorityRepository.findAllById(List.of(user.getUsername()));
                List<String> authorityList = new ArrayList<>();
                authorities.forEach(authority -> authorityList.add(authority.getAuthority()));

                userResponseList.add(
                    new UserResponse(
                            user.getUsername(),
                            user.getPassword(),
                            user.getEnabled(),
                            authorityList
                    )
                );
        });

        return userResponseList;
    }

}
