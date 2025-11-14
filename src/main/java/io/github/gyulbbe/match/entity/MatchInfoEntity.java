package io.github.gyulbbe.match.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "MATCH_INFO")
public class MatchInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LEAGUE_ID", nullable = false)
    private Long leagueId;

    @Column(name = "MATCH_TYPE")
    private String matchType;

    @Column(name = "FORMAT")
    private String format;

    @Column(name = "WINNER")
    private String winner;

    @Column(name = "LOSER")
    private String loser;

    @Column(name = "ROUND")
    private String round;

    @Column(name = "SETS")
    private String sets;

    @Column(name = "MATCH_DATE")
    private LocalDate matchDate;
}
