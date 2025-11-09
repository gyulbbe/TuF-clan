package io.github.gyulbbe.commentary.dto;

import lombok.Data;

@Data
public class CommentaryDto {
    private Long commentaryId;
    private Long matchInfoId;
    private String matchSummary;
}
