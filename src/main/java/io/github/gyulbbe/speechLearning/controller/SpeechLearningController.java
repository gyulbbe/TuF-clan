package io.github.gyulbbe.speechLearning.controller;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.speechLearning.dto.SpeechLearningDto;
import io.github.gyulbbe.speechLearning.service.SpeechLearningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/speech-learning")
@RequiredArgsConstructor
@RestController
public class SpeechLearningController {

    private final SpeechLearningService speechLearningService;

    /**
     * 채팅 리스트 저장 (임베딩 포함)
     * POST /speech-learning
     */
    @PostMapping
    public ResponseEntity<ResponseDto<String>> insertSpeechLearningList(@Valid @RequestBody List<SpeechLearningDto> dtoList) {
        log.info("채팅 리스트 저장 요청 - 개수: {}", dtoList.size());
        ResponseDto<String> response = speechLearningService.insertSpeechLearningList(dtoList);
        return ResponseEntity.ok(response);
    }
}
