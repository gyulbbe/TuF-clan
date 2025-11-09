package io.github.gyulbbe.common.utils.embeddingVector;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final EmbeddingModel embeddingModel; // Spring AI의 OllamaEmbeddingModel 자동 주입

    /**
     * 텍스트를 임베딩 벡터로 변환
     * @param text 임베딩할 텍스트
     * @return 임베딩 벡터
     */
    public float[] embed(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }

        try {
            EmbeddingResponse response = embeddingModel.embedForResponse(List.of(text));
            float[] result = response.getResults().get(0).getOutput();

            log.debug("Generated embedding vector of dimension: {}", result.length);
            return result;
        } catch (Exception e) {
            log.error("Embedding generation failed for text: {}", text, e);
            throw new RuntimeException("Failed to generate embedding: " + e.getMessage(), e);
        }
    }

    /**
     * 여러 텍스트를 배치로 임베딩
     * @param texts 임베딩할 텍스트 배열
     * @return 각 텍스트의 임베딩 벡터 배열
     */
    public float[][] embedBatch(String[] texts) {
        float[][] embeddings = new float[texts.length][];
        for (int i = 0; i < texts.length; i++) {
            embeddings[i] = embed(texts[i]);
        }
        return embeddings;
    }

    /**
     * 두 벡터 간의 코사인 유사도 계산
     * @param vector1 첫 번째 벡터
     * @param vector2 두 번째 벡터
     * @return 코사인 유사도 (0~1)
     */
    public static double cosineSimilarity(float[] vector1, float[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must have the same dimension");
        }

        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            norm1 += vector1[i] * vector1[i];
            norm2 += vector2[i] * vector2[i];
        }

        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    /**
     * 코사인 거리 계산 (1 - 코사인 유사도)
     * @param vector1 첫 번째 벡터
     * @param vector2 두 번째 벡터
     * @return 코사인 거리 (0~1, 작을수록 유사)
     */
    public static double cosineDistance(float[] vector1, float[] vector2) {
        return 1.0 - cosineSimilarity(vector1, vector2);
    }
}
