package io.github.gyulbbe.speechLearning.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SpeechLearningDto {
    private Long speechLearningId;
    private Long userId;
    private String text;
    private LocalDateTime createdAt;
}
