package io.github.gyulbbe.map.service;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.map.dto.insertMapDto;
import io.github.gyulbbe.map.mapper.MapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MapService {

    private final MapMapper mapMapper;

    public ResponseDto<Void> insertMap(insertMapDto insertMapDto) {
        int result = mapMapper.insertMap(insertMapDto);
        if (result > 0) {
            return ResponseDto.success(null);
        } else {
            return ResponseDto.fail("맵 등록에 실패했습니다.");
        }
    }
}
