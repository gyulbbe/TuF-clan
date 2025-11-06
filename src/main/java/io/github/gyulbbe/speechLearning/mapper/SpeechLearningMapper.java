package io.github.gyulbbe.speechLearning.mapper;

import io.github.gyulbbe.speechLearning.dto.insertSpeechLearningDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpeechLearningMapper {
    int insertSpeechLearning(insertSpeechLearningDto insertSpeechLearningDto);
}
