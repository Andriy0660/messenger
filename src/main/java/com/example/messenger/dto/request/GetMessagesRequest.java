package com.example.messenger.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMessagesRequest {
    private Long idOfFirstUser;
    private Long idOfSecondUser;
}
