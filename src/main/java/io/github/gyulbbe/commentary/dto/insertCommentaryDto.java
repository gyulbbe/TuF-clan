package io.github.gyulbbe.commentary.dto;

import lombok.Data;

@Data
public class insertCommentaryDto {
    private Long matchInfoId;
    private String matchSummary;
}
