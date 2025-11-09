package io.github.gyulbbe.user.dto;

import lombok.Data;

@Data
public class UserInsertDto {
    private String userId;

    private String race;

    private String tier;
}
