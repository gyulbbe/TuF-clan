package io.github.gyulbbe.league.service;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.league.dto.insertLeagueDto;
import io.github.gyulbbe.league.dto.insertLeagueParticipationDto;
import io.github.gyulbbe.league.dto.insertProleagueTeamDto;
import io.github.gyulbbe.league.mapper.LeagueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueMapper leagueMapper;

    public ResponseDto<Void> insertLeague(insertLeagueDto insertLeagueDto) {
        int result = leagueMapper.insertLeague(insertLeagueDto);
        if (result > 0) {
            return ResponseDto.success(null);
        } else {
            return ResponseDto.fail("리그 등록에 실패했습니다.");
        }
    }

    public ResponseDto<Void> insertLeagueParticipation(insertLeagueParticipationDto insertLeagueParticipationDto) {
        int result = leagueMapper.insertLeagueParticipation(insertLeagueParticipationDto);
        if (result > 0) {
            return ResponseDto.success(null);
        } else {
            return ResponseDto.fail("리그 참가 등록에 실패했습니다.");
        }
    }

    public ResponseDto<Void> insertProleagueTeam(insertProleagueTeamDto insertProleagueTeamDto) {
        int result = leagueMapper.insertProleagueTeam(insertProleagueTeamDto);
        if (result > 0) {
            return ResponseDto.success(null);
        } else {
            return ResponseDto.fail("프로리그 팀 등록에 실패했습니다.");
        }
    }
}
