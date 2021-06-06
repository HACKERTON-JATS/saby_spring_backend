package com.jats.savy.savy.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jats.savy.savy.SavyApplication;
import com.jats.savy.savy.entity.admin.Admin;
import com.jats.savy.savy.entity.admin.AdminRepository;
import com.jats.savy.savy.entity.chat.Chat;
import com.jats.savy.savy.entity.chat.ChatRepository;
import com.jats.savy.savy.entity.room.Room;
import com.jats.savy.savy.entity.room.RoomRepository;
import com.jats.savy.savy.entity.user.User;
import com.jats.savy.savy.entity.user.UserRepository;
import com.jats.savy.savy.payload.response.UserListResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes = SavyApplication.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    Room room;
    User user;
    Admin admin;

    @BeforeEach
    void setUp() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        admin = adminRepository.save(Admin.builder()
                .code("code")
                .build());

        user = userRepository.save(
                User.builder()
                        .userId("test")
                        .email("test")
                        .nickname("nickname")
                        .password("pwd")
                        .build()
        );
        room = roomRepository.save(
                Room.builder()
                        .user(user)
                        .admin(admin)
                        .build()
        );
    }

    @AfterEach
    void cleanUp() {
        chatRepository.deleteAll();
        roomRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = "test")
    void getUserListSuccessful() throws Exception {
        MvcResult result = mvc.perform(get("/admin/user")
                .param("page", "0"))
                .andExpect(status().isOk())
                .andReturn();

        UserListResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), UserListResponse.class);

        Assertions.assertNull(response.getUserContentResponses().get(0).getLastChat());
        Assertions.assertEquals(response.getUserContentResponses().get(0).getUserEmail(), "test");
    }

    @Test
    @WithMockUser(value = "test")
    void getUserListSuccess() throws Exception {
        createChat("asdf");

        MvcResult result = mvc.perform(get("/admin/user")
                .param("page", "0"))
                .andExpect(status().isOk())
                .andReturn();

        UserListResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), UserListResponse.class);

        Assertions.assertEquals(response.getUserContentResponses().get(0).getLastChat(), "asdf");
        Assertions.assertEquals(response.getUserContentResponses().get(0).getUserEmail(), "test");
    }

    private void createChat(String message) {
        chatRepository.save(
                Chat.builder()
                        .message(message)
                        .room(room)
                        .user(user)
                        .isAdmin(true)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }
}
