package com.jats.savy.savy.service.user;

import com.jats.savy.savy.payload.response.UserListResponse;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserListResponse getUserList(Pageable pageable);
}
