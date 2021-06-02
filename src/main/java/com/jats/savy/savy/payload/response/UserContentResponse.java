package com.jats.savy.savy.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserContentResponse {

    private String nickName;

    private Long roomId;

    private String userEmail;

    private String lastChat;
}
