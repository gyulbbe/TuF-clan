package io.github.gyulbbe.speechLearning.controller;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.speechLearning.dto.SpeechLearningDto;
import io.github.gyulbbe.speechLearning.service.SpeechLearningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/speech-learning")
@RequiredArgsConstructor
@RestController
public class SpeechLearningController {

    private final SpeechLearningService speechLearningService;

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<SpeechLearningDto>>> list() {
        return ResponseEntity.ok(speechLearningService.findAllSpeechLearning());
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<Void>> insertSpeechLearning(@Valid @RequestBody SpeechLearningDto speechLearningDto) {
        return ResponseEntity.ok(speechLearningService.insertSpeechLearning(speechLearningDto));
    }

    @PostMapping("/embed-all")
    public ResponseEntity<ResponseDto<String>> embedAllSpeechLearning() {
        return ResponseEntity.ok(speechLearningService.embedAllSpeechLearning());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto<SpeechLearningDto>> findSpeechLearningById(@RequestBody SpeechLearningDto speechLearningDto) {
        return ResponseEntity.ok(speechLearningService.findSpeechLearningById(speechLearningDto.getId()));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto<Void>> updateSpeechLearning(@Valid @RequestBody SpeechLearningDto speechLearningDto) {
        return ResponseEntity.ok(speechLearningService.updateSpeechLearning(speechLearningDto.getId(), speechLearningDto));
    }
}
