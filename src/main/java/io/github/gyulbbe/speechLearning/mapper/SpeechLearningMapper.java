package io.github.gyulbbe.speechLearning.mapper;

import io.github.gyulbbe.speechLearning.dto.SpeechLearningDto;
import io.github.gyulbbe.speechLearning.dto.insertSpeechLearningDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpeechLearningMapper {
    int insertSpeechLearning(insertSpeechLearningDto insertSpeechLearningDto);
    List<SpeechLearningDto> findAllSpeechLearning();
}
