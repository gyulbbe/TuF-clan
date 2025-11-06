package io.github.gyulbbe.common.utils.embeddingVector;

import io.github.gyulbbe.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/embedding-vector")
@RequiredArgsConstructor
@RestController
public class EmbeddingVectorController {

    private final EmbeddingVectorService embeddingVectorService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<Void>> insertEmbeddingVector(@Valid @RequestBody EmbeddingVectorDto embeddingVectorDto) {
        return ResponseEntity.ok(embeddingVectorService.insertEmbeddingVector(embeddingVectorDto));
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseDto<EmbeddingVectorDto>> findSimilarVectors(@Valid @RequestBody SimilaritySearchRequestDto requestDto) {
        return ResponseEntity.ok(embeddingVectorService.findMostSimilarVector(requestDto));
    }
}
