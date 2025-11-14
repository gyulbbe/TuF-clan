package io.github.gyulbbe.commentary.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "COMMENTARY")
public class CommentaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MATCH_INFO_ID", nullable = false)
    private Long matchInfoId;

    @Column(name = "MATCH_SUMMARY")
    private String matchSummary;
}