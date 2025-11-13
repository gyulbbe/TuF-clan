package io.github.gyulbbe.common.utils.embeddingVector;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmbeddingVectorMapper {
    int insertEmbeddingVector(EmbeddingVectorDto embeddingVectorDto);
    List<EmbeddingVectorDto> findAllVectors(@Param("referenceTable") String referenceTable);
    List<EmbeddingVectorDto> findSimilarVectors(SimilaritySearchRequestDto requestDto);
    EmbeddingVectorDto findMostSimilarVector(SimilaritySearchRequestDto requestDto);
}