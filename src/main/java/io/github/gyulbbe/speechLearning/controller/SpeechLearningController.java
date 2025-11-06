package io.github.gyulbbe.speechLearning.controller;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.speechLearning.dto.insertSpeechLearningDto;
import io.github.gyulbbe.speechLearning.service.SpeechLearningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/speech-learning")
@RequiredArgsConstructor
@RestController
public class SpeechLearningController {

    private final SpeechLearningService speechLearningService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<Void>> insertSpeechLearning(@Valid @RequestBody insertSpeechLearningDto insertSpeechLearningDto) {
        return ResponseEntity.ok(speechLearningService.insertSpeechLearning(insertSpeechLearningDto));
    }

    @GetMapping("/test")
    public void test() {
        System.out.println("테스트");
    }
}
