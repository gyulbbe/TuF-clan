package io.github.gyulbbe.league.mapper;

import io.github.gyulbbe.league.dto.insertLeagueDto;
import io.github.gyulbbe.league.dto.insertLeagueParticipationDto;
import io.github.gyulbbe.league.dto.insertProleagueTeamDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeagueMapper {
    int insertLeague(insertLeagueDto insertLeagueDto);
    int insertLeagueParticipation(insertLeagueParticipationDto insertLeagueParticipationDto);
    int insertProleagueTeam(insertProleagueTeamDto insertProleagueTeamDto);
}
