package io.github.gyulbbe.map.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "MAP")
public class MapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MATCH_INFO_ID", nullable = false)
    private Long matchInfoId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;
}
