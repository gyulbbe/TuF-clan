package io.github.gyulbbe.match.service;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.match.dto.insertMatchInfoDto;
import io.github.gyulbbe.match.mapper.MatchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchService {

    private final MatchMapper matchMapper;

    public ResponseDto<Void> insertMatchInfo(insertMatchInfoDto insertMatchInfoDto) {
        int result = matchMapper.insertMatchInfo(insertMatchInfoDto);
        if (result > 0) {
            return ResponseDto.success(null);
        } else {
            return ResponseDto.fail("매치 정보 등록에 실패했습니다.");
        }
    }
}
