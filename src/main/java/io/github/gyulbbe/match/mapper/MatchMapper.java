package io.github.gyulbbe.match.mapper;

import io.github.gyulbbe.match.dto.insertMatchInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MatchMapper {
    int insertMatchInfo(insertMatchInfoDto insertMatchInfoDto);
}
