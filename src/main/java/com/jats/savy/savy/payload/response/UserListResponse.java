package com.jats.savy.savy.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListResponse {

    private Integer totalPages;

    private List<UserContentResponse> userContentResponses;

}
