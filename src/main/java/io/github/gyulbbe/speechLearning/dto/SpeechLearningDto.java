package io.github.gyulbbe.speechLearning.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SpeechLearningDto {
    private String nickname;
    private String chat;
    private LocalDateTime chatDatetime;
}
