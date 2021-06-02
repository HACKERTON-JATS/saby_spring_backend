package com.jats.savy.savy.service.user;

import com.jats.savy.savy.entity.user.User;
import com.jats.savy.savy.entity.user.UserRepository;
import com.jats.savy.savy.payload.response.UserContentResponse;
import com.jats.savy.savy.payload.response.UserListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserListResponse getUserList(Pageable pageable) {
        Page<User> users = userRepository.findAllBy(pageable);

        return UserListResponse.builder()
                .userContentResponses(users.map(user -> UserContentResponse.builder()
                        .lastChat(user.getChats().stream()
                                .findFirst().isEmpty() ? null : user.getChats().stream().findFirst().get().getMessage())
                        .nickName(user.getNickname())
                        .userEmail(user.getEmail())
                        .roomId(user.getRoom().getId())
                        .build())
                        .stream().collect(Collectors.toList()))
                .totalPages(users.getTotalPages())
                .build();
    }
}
