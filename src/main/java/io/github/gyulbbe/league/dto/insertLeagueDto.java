package io.github.gyulbbe.league.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class insertLeagueDto {
    private String leagueName;
    private LocalDate startDate;
    private LocalDate endDate;
}
