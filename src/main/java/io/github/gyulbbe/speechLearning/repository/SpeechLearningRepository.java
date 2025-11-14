package io.github.gyulbbe.speechLearning.repository;

import io.github.gyulbbe.speechLearning.entity.SpeechLearningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeechLearningRepository extends JpaRepository<SpeechLearningEntity, Long> {
}
