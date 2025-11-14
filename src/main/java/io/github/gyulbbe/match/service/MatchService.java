package io.github.gyulbbe.match.service;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.match.dto.MatchInfoDto;
import io.github.gyulbbe.match.entity.MatchInfoEntity;
import io.github.gyulbbe.match.repository.MatchInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MatchService {

    private final MatchInfoRepository matchInfoRepository;

    public ResponseDto<Void> insertMatchInfo(MatchInfoDto matchInfoDto) {
        try {
            MatchInfoEntity entity = new MatchInfoEntity();
            entity.setLeagueId(matchInfoDto.getLeagueId());
            entity.setMatchType(matchInfoDto.getMatchType());
            entity.setFormat(matchInfoDto.getFormat());
            entity.setWinner(matchInfoDto.getWinner());
            entity.setLoser(matchInfoDto.getLoser());
            entity.setRound(matchInfoDto.getRound());
            entity.setSets(matchInfoDto.getSets());
            entity.setMatchDate(matchInfoDto.getMatchDate());

            matchInfoRepository.save(entity);
            return ResponseDto.success(null);
        } catch (Exception e) {
            log.error("매치 정보 등록 실패", e);
            return ResponseDto.fail("매치 정보 등록에 실패했습니다.");
        }
    }

    public ResponseDto<MatchInfoDto> findMatchInfoById(Long id) {
        try {
            MatchInfoEntity entity = matchInfoRepository.findById(id)
                    .orElse(null);

            if (entity == null) {
                return ResponseDto.fail("매치 정보를 찾을 수 없습니다.");
            }

            MatchInfoDto dto = new MatchInfoDto();
            dto.setId(entity.getId());
            dto.setLeagueId(entity.getLeagueId());
            dto.setMatchType(entity.getMatchType());
            dto.setFormat(entity.getFormat());
            dto.setWinner(entity.getWinner());
            dto.setLoser(entity.getLoser());
            dto.setRound(entity.getRound());
            dto.setSets(entity.getSets());
            dto.setMatchDate(entity.getMatchDate());

            return ResponseDto.success(dto);
        } catch (Exception e) {
            log.error("매치 정보 조회 실패", e);
            return ResponseDto.fail("매치 정보 조회에 실패했습니다.");
        }
    }

    public ResponseDto<Void> updateMatchInfo(Long id, MatchInfoDto matchInfoDto) {
        try {
            MatchInfoEntity entity = matchInfoRepository.findById(id)
                    .orElse(null);

            if (entity == null) {
                return ResponseDto.fail("매치 정보를 찾을 수 없습니다.");
            }

            entity.setLeagueId(matchInfoDto.getLeagueId());
            entity.setMatchType(matchInfoDto.getMatchType());
            entity.setFormat(matchInfoDto.getFormat());
            entity.setWinner(matchInfoDto.getWinner());
            entity.setLoser(matchInfoDto.getLoser());
            entity.setRound(matchInfoDto.getRound());
            entity.setSets(matchInfoDto.getSets());
            entity.setMatchDate(matchInfoDto.getMatchDate());

            matchInfoRepository.save(entity);
            return ResponseDto.success(null);
        } catch (Exception e) {
            log.error("매치 정보 수정 실패", e);
            return ResponseDto.fail("매치 정보 수정에 실패했습니다.");
        }
    }

    public ResponseDto<List<MatchInfoDto>> list() {
        try {
            List<MatchInfoEntity> entities = matchInfoRepository.findAll();

            List<MatchInfoDto> dtos = entities.stream().map(entity -> {
                MatchInfoDto dto = new MatchInfoDto();
                dto.setId(entity.getId());
                dto.setLeagueId(entity.getLeagueId());
                dto.setMatchType(entity.getMatchType());
                dto.setFormat(entity.getFormat());
                dto.setWinner(entity.getWinner());
                dto.setLoser(entity.getLoser());
                dto.setRound(entity.getRound());
                dto.setSets(entity.getSets());
                dto.setMatchDate(entity.getMatchDate());
                return dto;
            }).toList();

            return ResponseDto.success(dtos);
        } catch (Exception e) {
            log.error("매치 정보 목록 조회 실패", e);
            return ResponseDto.fail("매치 정보 목록 조회에 실패했습니다.");
        }
    }
}
