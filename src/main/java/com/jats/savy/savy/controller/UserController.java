package com.jats.savy.savy.controller;

import com.jats.savy.savy.payload.response.UserListResponse;
import com.jats.savy.savy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/admin/user")
    public UserListResponse getUserList(Pageable pageable) {
        return userService.getUserList(pageable);
    }
}
