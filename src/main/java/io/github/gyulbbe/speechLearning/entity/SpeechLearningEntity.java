package io.github.gyulbbe.speechLearning.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "SPEECH_STYLE_LEARNING")
public class SpeechLearningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @Column(name = "CHAT")
    private String chat;

    @Column(name = "CHAT_EMBEDDING_VECTOR", columnDefinition = "VECTOR")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private float[] chatEmbeddingVector;

    @CreationTimestamp
    @Column(name = "CHAT_DATETIME", updatable = false)
    private LocalDateTime chatDatetime;
}
