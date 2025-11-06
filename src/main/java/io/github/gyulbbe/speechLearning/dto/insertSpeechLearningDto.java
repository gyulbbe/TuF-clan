package io.github.gyulbbe.speechLearning.dto;

import lombok.Data;

@Data
public class insertSpeechLearningDto {
    private Long userId;
    private String text;
}
