package io.github.gyulbbe.speechLearning.service;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.common.utils.embeddingVector.EmbeddingService;
import io.github.gyulbbe.speechLearning.dto.SpeechLearningDto;
import io.github.gyulbbe.speechLearning.entity.SpeechLearningEntity;
import io.github.gyulbbe.speechLearning.repository.SpeechLearningRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SpeechLearningService {

    private final SpeechLearningRepository speechLearningRepository;
    private final EmbeddingService embeddingService;

    public ResponseDto<Void> insertSpeechLearning(SpeechLearningDto speechLearningDto) {
        try {
            SpeechLearningEntity entity = new SpeechLearningEntity();
            entity.setUserId(speechLearningDto.getUserId());
            entity.setText(speechLearningDto.getText());

            speechLearningRepository.save(entity);
            return ResponseDto.success(null);
        } catch (Exception e) {
            log.error("명대사 학습 등록 실패", e);
            return ResponseDto.fail("명대사 학습 등록에 실패했습니다.");
        }
    }

    public ResponseDto<SpeechLearningDto> findSpeechLearningById(Long id) {
        try {
            SpeechLearningEntity entity = speechLearningRepository.findById(id)
                    .orElse(null);

            if (entity == null) {
                return ResponseDto.fail("명대사 학습을 찾을 수 없습니다.");
            }

            SpeechLearningDto dto = new SpeechLearningDto();
            dto.setId(entity.getId());
            dto.setUserId(entity.getUserId());
            dto.setText(entity.getText());
            dto.setCreatedAt(entity.getCreatedAt());

            return ResponseDto.success(dto);
        } catch (Exception e) {
            log.error("명대사 학습 조회 실패", e);
            return ResponseDto.fail("명대사 학습 조회에 실패했습니다.");
        }
    }

    public ResponseDto<Void> updateSpeechLearning(Long id, SpeechLearningDto speechLearningDto) {
        try {
            SpeechLearningEntity entity = speechLearningRepository.findById(id)
                    .orElse(null);

            if (entity == null) {
                return ResponseDto.fail("명대사 학습을 찾을 수 없습니다.");
            }

            entity.setUserId(speechLearningDto.getUserId());
            entity.setText(speechLearningDto.getText());

            speechLearningRepository.save(entity);
            return ResponseDto.success(null);
        } catch (Exception e) {
            log.error("명대사 학습 수정 실패", e);
            return ResponseDto.fail("명대사 학습 수정에 실패했습니다.");
        }
    }

    /**
     * 모든 말투 학습 데이터 조회
     */
    public ResponseDto<List<SpeechLearningDto>> findAllSpeechLearning() {
        List<SpeechLearningEntity> speechLearnings = speechLearningRepository.findAll();
        List<SpeechLearningDto> speechLearningDtos = new ArrayList<>();
        for (SpeechLearningEntity speechLearningEntity : speechLearnings) {
            SpeechLearningDto speechLearningDto = new SpeechLearningDto();
            speechLearningDto.setId(speechLearningEntity.getId());
            speechLearningDto.setText(speechLearningEntity.getText());
            speechLearningDto.setUserId(speechLearningEntity.getUserId());
            speechLearningDto.setCreatedAt(speechLearningEntity.getCreatedAt());
            speechLearningDtos.add(speechLearningDto);
        }
        return ResponseDto.success(speechLearningDtos);
    }

    /**
     * SPEECH_STYLE_LEARNING 테이블의 모든 데이터를 임베딩하여 VECTORS 테이블에 저장
     */
    public ResponseDto<String> embedAllSpeechLearning() {
        try {
            // 모든 SpeechLearning 조회
            List<SpeechLearningEntity> speechLearnings = speechLearningRepository.findAll();

            if (speechLearnings.isEmpty()) {
                return ResponseDto.fail("저장된 말투 학습 데이터가 없습니다.");
            }

            int successCount = 0;
            int failCount = 0;

            // 각 SpeechLearning을 임베딩하여 저장
            for (SpeechLearningEntity speechLearning : speechLearnings) {
                try {
                    int result = embeddingService.embedAndSave(
                            speechLearning.getId(),
                            "SPEECH_STYLE_LEARNING",
                            speechLearning.getText()
                    );

                    if (result > 0) {
                        successCount++;
                        log.info("SpeechLearning ID {} 임베딩 성공", speechLearning.getId());
                    } else {
                        failCount++;
                        log.error("SpeechLearning ID {} 임베딩 실패", speechLearning.getId());
                    }
                } catch (Exception e) {
                    failCount++;
                    log.error("SpeechLearning ID {} 임베딩 중 오류 발생", speechLearning.getId(), e);
                }
            }

            String message = String.format("임베딩 완료 - 성공: %d, 실패: %d, 전체: %d",
                                           successCount, failCount, speechLearnings.size());
            log.info(message);

            return ResponseDto.success(message);
        } catch (Exception e) {
            log.error("배치 임베딩 처리 중 오류 발생", e);
            return ResponseDto.fail("배치 임베딩 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
