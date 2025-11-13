package io.github.gyulbbe.speechLearning.service;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.common.utils.embeddingVector.EmbeddingService;
import io.github.gyulbbe.common.utils.embeddingVector.EmbeddingVectorDto;
import io.github.gyulbbe.speechLearning.dto.SpeechLearningDto;
import io.github.gyulbbe.speechLearning.dto.insertSpeechLearningDto;
import io.github.gyulbbe.speechLearning.mapper.SpeechLearningMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SpeechLearningService {

    private final SpeechLearningMapper speechLearningMapper;
    private final EmbeddingService embeddingService;

    public ResponseDto<Void> insertSpeechLearning(insertSpeechLearningDto insertSpeechLearningDto) {
        int result = speechLearningMapper.insertSpeechLearning(insertSpeechLearningDto);
        if (result > 0) {
            return ResponseDto.success(null);
        } else {
            return ResponseDto.fail("명대사 학습 등록에 실패했습니다.");
        }
    }

    /**
     * 모든 말투 학습 데이터 조회
     */
    public ResponseDto<List<SpeechLearningDto>> findAllSpeechLearning() {
        List<SpeechLearningDto> speechLearnings = speechLearningMapper.findAllSpeechLearning();
        return ResponseDto.success(speechLearnings);
    }

    /**
     * SPEECH_STYLE_LEARNING 테이블의 모든 데이터를 임베딩하여 VECTORS 테이블에 저장
     */
    public ResponseDto<String> embedAllSpeechLearning() {
        try {
            // 모든 SpeechLearning 조회
            List<SpeechLearningDto> speechLearnings = speechLearningMapper.findAllSpeechLearning();

            if (speechLearnings == null || speechLearnings.isEmpty()) {
                return ResponseDto.fail("저장된 말투 학습 데이터가 없습니다.");
            }

            int successCount = 0;
            int failCount = 0;

            // 각 SpeechLearning을 임베딩하여 저장
            for (SpeechLearningDto speechLearning : speechLearnings) {
                try {
                    int result = embeddingService.embedAndSave(
                            speechLearning.getSpeechLearningId(),
                            "SPEECH_STYLE_LEARNING",
                            speechLearning.getText()
                    );

                    if (result > 0) {
                        successCount++;
                        log.info("SpeechLearning ID {} 임베딩 성공", speechLearning.getSpeechLearningId());
                    } else {
                        failCount++;
                        log.error("SpeechLearning ID {} 임베딩 실패", speechLearning.getSpeechLearningId());
                    }
                } catch (Exception e) {
                    failCount++;
                    log.error("SpeechLearning ID {} 임베딩 중 오류 발생", speechLearning.getSpeechLearningId(), e);
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
