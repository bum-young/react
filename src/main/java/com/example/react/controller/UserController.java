package com.example.react.controller;

import com.example.react.model.CurrentUser;
import com.example.react.model.UserSummary;
import com.example.react.security.model.UserPrincipal;
import com.example.react.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<?> usersName(@PathVariable String name) {
        log.info("=] name {}", name);

        UserPrincipal principal = new UserPrincipal(Long.valueOf("1"),
                name
                , "usernameValue"
                , "test@email.com"
                , "pass"
                , null);

        return ResponseEntity.ok(principal);
    }
}
