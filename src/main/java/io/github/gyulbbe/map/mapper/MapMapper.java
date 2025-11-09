package io.github.gyulbbe.map.mapper;

import io.github.gyulbbe.map.dto.insertMapDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapMapper {
    int insertMap(insertMapDto insertMapDto);
}
