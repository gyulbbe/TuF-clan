package io.github.gyulbbe.league.controller;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.league.dto.insertLeagueDto;
import io.github.gyulbbe.league.dto.insertLeagueParticipationDto;
import io.github.gyulbbe.league.dto.insertProleagueTeamDto;
import io.github.gyulbbe.league.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/league")
@RequiredArgsConstructor
@RestController
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<Void>> insertLeague(@Valid @RequestBody insertLeagueDto insertLeagueDto) {
        return ResponseEntity.ok(leagueService.insertLeague(insertLeagueDto));
    }

    @PostMapping("/insert/participation")
    public ResponseEntity<ResponseDto<Void>> insertLeagueParticipation(@Valid @RequestBody insertLeagueParticipationDto insertLeagueParticipationDto) {
        return ResponseEntity.ok(leagueService.insertLeagueParticipation(insertLeagueParticipationDto));
    }

    @PostMapping("/insert/team")
    public ResponseEntity<ResponseDto<Void>> insertProleagueTeam(@Valid @RequestBody insertProleagueTeamDto insertProleagueTeamDto) {
        return ResponseEntity.ok(leagueService.insertProleagueTeam(insertProleagueTeamDto));
    }

    @GetMapping("/test")
    public void test() {
        System.out.println("테스트");
    }
}
