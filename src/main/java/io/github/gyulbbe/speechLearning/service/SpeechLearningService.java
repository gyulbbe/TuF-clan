package io.github.gyulbbe.speechLearning.service;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.speechLearning.dto.insertSpeechLearningDto;
import io.github.gyulbbe.speechLearning.mapper.SpeechLearningMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SpeechLearningService {

    private final SpeechLearningMapper speechLearningMapper;

    public ResponseDto<Void> insertSpeechLearning(insertSpeechLearningDto insertSpeechLearningDto) {
        int result = speechLearningMapper.insertSpeechLearning(insertSpeechLearningDto);
        if (result > 0) {
            return ResponseDto.success(null);
        } else {
            return ResponseDto.fail("명대사 학습 등록에 실패했습니다.");
        }
    }
}
