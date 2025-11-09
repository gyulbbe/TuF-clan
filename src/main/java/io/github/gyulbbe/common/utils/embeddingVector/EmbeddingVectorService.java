package io.github.gyulbbe.common.utils.embeddingVector;

import io.github.gyulbbe.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmbeddingVectorService {
    private final EmbeddingVectorMapper embeddingVectorMapper;
    private final EmbeddingService embeddingService;

    /**
     * 텍스트를 임베딩하여 DB에 저장
     */
    public ResponseDto<Void> insertEmbeddingVector(EmbeddingVectorDto embeddingVectorDto) {
        try {
            // KoSimCSE 모델을 사용하여 텍스트 임베딩 생성
            float[] embedding = embeddingService.embed(embeddingVectorDto.getText());
            embeddingVectorDto.setEmbeddingVector(embedding);

            // DB에 저장
            int result = embeddingVectorMapper.insertEmbeddingVector(embeddingVectorDto);
            if (result > 0) {
                return ResponseDto.success(null);
            } else {
                return ResponseDto.fail("임베딩 벡터 등록에 실패했습니다.");
            }
        } catch (Exception e) {
            log.error("임베딩 생성 실패", e);
            return ResponseDto.fail("임베딩 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 유사한 벡터 검색 (Java에서 코사인 유사도 계산)
     */
    public ResponseDto<List<EmbeddingVectorDto>> findSimilarVectors(SimilaritySearchRequestDto requestDto) {
        try {
            // 쿼리 텍스트를 임베딩으로 변환
            float[] queryEmbedding = embeddingService.embed(requestDto.getQueryText());

            // DB에서 모든 벡터 조회 (referenceType 필터링 적용)
            List<EmbeddingVectorDto> allVectors = embeddingVectorMapper.findAllVectors(requestDto.getReferenceType());

            if (allVectors == null || allVectors.isEmpty()) {
                return ResponseDto.fail("저장된 벡터가 없습니다.");
            }

            // 각 벡터와의 코사인 거리 계산
            List<EmbeddingVectorDto> results = allVectors.stream()
                    .peek(vector -> {
                        double distance = embeddingService.cosineDistance(queryEmbedding, vector.getEmbeddingVector());
                        vector.setDistance(distance);
                    })
                    .sorted(Comparator.comparingDouble(EmbeddingVectorDto::getDistance))
                    .limit(requestDto.getTopK())
                    .collect(Collectors.toList());

            return ResponseDto.success(results);
        } catch (Exception e) {
            log.error("쿼리 임베딩 생성 실패", e);
            return ResponseDto.fail("쿼리 임베딩 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 가장 유사한 벡터 하나 검색
     */
    public ResponseDto<EmbeddingVectorDto> findMostSimilarVector(SimilaritySearchRequestDto requestDto) {
        try {
            // 쿼리 텍스트를 임베딩으로 변환
            float[] queryEmbedding = embeddingService.embed(requestDto.getQueryText());

            // DB에서 모든 벡터 조회 (referenceType 필터링 적용)
            List<EmbeddingVectorDto> allVectors = embeddingVectorMapper.findAllVectors(requestDto.getReferenceType());

            if (allVectors == null || allVectors.isEmpty()) {
                return ResponseDto.fail("저장된 벡터가 없습니다.");
            }

            // 가장 유사한 벡터 찾기
            EmbeddingVectorDto mostSimilar = allVectors.stream()
                    .peek(vector -> {
                        double distance = embeddingService.cosineDistance(queryEmbedding, vector.getEmbeddingVector());
                        vector.setDistance(distance);
                    })
                    .min(Comparator.comparingDouble(EmbeddingVectorDto::getDistance))
                    .orElse(null);

            if (mostSimilar != null) {
                return ResponseDto.success(mostSimilar);
            } else {
                return ResponseDto.fail("유사한 벡터를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            log.error("쿼리 임베딩 생성 실패", e);
            return ResponseDto.fail("쿼리 임베딩 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
