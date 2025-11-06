package io.github.gyulbbe.common.utils.embeddingVector;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SimilaritySearchRequestDto {
    @NotNull
    private String queryText;

    @Min(1)
    private Integer topK = 10;

    private String referenceType;
}
