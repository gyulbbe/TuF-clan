package io.github.gyulbbe.league.dto;

import lombok.Data;

@Data
public class insertLeagueParticipationDto {
    private Long leagueId;
    private Long userId;
    private String race;
}
