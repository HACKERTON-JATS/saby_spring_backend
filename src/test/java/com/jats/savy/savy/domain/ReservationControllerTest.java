package com.jats.savy.savy.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jats.savy.savy.SavyApplication;
import com.jats.savy.savy.entity.kidinformation.KidInformation;
import com.jats.savy.savy.entity.kidinformation.KidInformationRepository;
import com.jats.savy.savy.entity.reservation.Reservation;
import com.jats.savy.savy.entity.reservation.ReservationRepository;
import com.jats.savy.savy.entity.user.User;
import com.jats.savy.savy.entity.user.UserRepository;
import com.jats.savy.savy.payload.response.ReservationList;
import com.jats.savy.savy.payload.response.ReservationResponse;
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
public class ReservationControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private KidInformationRepository kidInformationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    private User user;

    private Reservation success;

    @BeforeEach
    void setUp() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        user = userRepository.save(
                User.builder()
                        .userId("idid")
                        .password("password")
                        .nickname("nickname")
                        .build()
        );
        success = createReservation(true, true, "request11");
        createReservation(false, false, "request22");
    }

    @AfterEach
    void cleanUp() {
        kidInformationRepository.deleteAll();
        reservationRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = "test")
    void getReservationListSuccess() throws Exception {
        MvcResult result = mvc.perform(get("/admin/reservation")
                .param("page", "0"))
                .andExpect(status().isOk())
                .andReturn();

        ReservationList reservationList = objectMapper.registerModule(new JavaTimeModule()).readValue(result.getResponse().getContentAsString(), ReservationList.class);

        Assertions.assertEquals(reservationList.getTotalPages(), 1);
        Assertions.assertEquals(reservationList.getReservationInfos().size(), 2);
    }

    @Test
    @WithMockUser(value = "test")
    void getReservationSuccess() throws Exception {
        MvcResult result = mvc.perform(get("/admin/reservation/" + success.getId()))
                .andExpect(status().isOk())
                .andReturn();

        ReservationResponse response = objectMapper.registerModule(new JavaTimeModule()).readValue(result.getResponse().getContentAsString(), ReservationResponse.class);

        Assertions.assertFalse(response.isReservation());
    }

    private Reservation createReservation(boolean isReservation, boolean isTake, String request) {
        Reservation reservation = Reservation.builder()
                .isReservation(isReservation)
                .isTake(isTake)
                .time(LocalDateTime.now().plusDays(2))
                .user(user)
                .build();

        kidInformationRepository.save(
                KidInformation.builder()
                        .birthDate(LocalDateTime.now())
                        .caution("caution")
                        .fetusName("fetus")
                        .giveLater("give later")
                        .kidName("name")
                        .request(request)
                        .vaccination("vaccinate")
                        .reservation(reservation)
                        .build()
        );
        return reservation;

    }
}
