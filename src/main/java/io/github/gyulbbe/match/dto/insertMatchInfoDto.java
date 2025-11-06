package io.github.gyulbbe.match.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class insertMatchInfoDto {
    private Long leagueId;
    private String matchType;
    private String format;
    private String winner;
    private String loser;
    private String round;
    private String sets;
    private LocalDate matchDate;
}
