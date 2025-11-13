package io.github.gyulbbe.commentary.service;

import io.github.gyulbbe.commentary.dto.CommentaryDto;
import io.github.gyulbbe.commentary.dto.insertCommentaryDto;
import io.github.gyulbbe.commentary.mapper.CommentaryMapper;
import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.common.utils.embeddingVector.EmbeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentaryService {

    private final CommentaryMapper commentaryMapper;
    private final EmbeddingService embeddingService;

    public ResponseDto<Void> insertCommentary(insertCommentaryDto insertCommentaryDto) {
        int result = commentaryMapper.insertCommentary(insertCommentaryDto);
        if (result > 0) {
            return ResponseDto.success(null);
        } else {
            return ResponseDto.fail("해설 등록에 실패했습니다.");
        }
    }

    /**
     * COMMENTARIES 테이블의 모든 데이터를 임베딩하여 VECTORS 테이블에 저장
     */
    public ResponseDto<String> embedAllCommentaries() {
        try {
            // 모든 Commentary 조회
            List<CommentaryDto> commentaries = commentaryMapper.findAllCommentaries();

            if (commentaries == null || commentaries.isEmpty()) {
                return ResponseDto.fail("저장된 해설 데이터가 없습니다.");
            }

            int successCount = 0;
            int failCount = 0;

            // 각 Commentary를 임베딩하여 저장
            for (CommentaryDto commentary : commentaries) {
                try {
                    int result = embeddingService.embedAndSave(
                            commentary.getCommentaryId(),
                            "COMMENTARIES",
                            commentary.getMatchSummary()
                    );

                    if (result > 0) {
                        successCount++;
                        log.info("Commentary ID {} 임베딩 성공", commentary.getCommentaryId());
                    } else {
                        failCount++;
                        log.error("Commentary ID {} 임베딩 실패", commentary.getCommentaryId());
                    }
                } catch (Exception e) {
                    failCount++;
                    log.error("Commentary ID {} 임베딩 중 오류 발생", commentary.getCommentaryId(), e);
                }
            }

            String message = String.format("임베딩 완료 - 성공: %d, 실패: %d, 전체: %d",
                                           successCount, failCount, commentaries.size());
            log.info(message);

            return ResponseDto.success(message);
        } catch (Exception e) {
            log.error("배치 임베딩 처리 중 오류 발생", e);
            return ResponseDto.fail("배치 임베딩 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
