package io.github.gyulbbe.league.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "LEAGUE_PARTICIPATION")
public class LeagueParticipationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LEAGUE_ID", nullable = false)
    private Long leagueId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "RACE")
    private String race;

    @Column(name = "STATUS")
    private String status = "ACTIVE";
}
