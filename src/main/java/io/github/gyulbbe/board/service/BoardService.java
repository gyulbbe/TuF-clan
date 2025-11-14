package io.github.gyulbbe.board.service;

import io.github.gyulbbe.board.dto.BoardDto;
import io.github.gyulbbe.board.entity.BoardEntity;
import io.github.gyulbbe.board.repository.BoardRepository;
import io.github.gyulbbe.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public ResponseDto<Void> insert(BoardDto boardDto) {
        try {
            BoardEntity entity = new BoardEntity();
            entity.setUserId(boardDto.getUserId());
            entity.setTitle(boardDto.getTitle());
            entity.setText(boardDto.getText());
            entity.setType(boardDto.getType());
            entity.setCreatedDatetime(new Date());

            boardRepository.save(entity);
            return ResponseDto.success(null);
        } catch (Exception e) {
            log.error("게시글 등록 실패", e);
            return ResponseDto.fail("게시글 등록에 실패했습니다.");
        }
    }

    public ResponseDto<BoardDto> get(Long id) {
        try {
            BoardEntity entity = boardRepository.findById(id)
                    .orElse(null);

            if (entity == null) {
                return ResponseDto.fail("게시글을 찾을 수 없습니다.");
            }

            BoardDto dto = new BoardDto();
            dto.setId(entity.getId());
            dto.setUserId(entity.getUserId());
            dto.setTitle(entity.getTitle());
            dto.setText(entity.getText());
            dto.setType(entity.getType());
            dto.setCreatedDatetime(entity.getCreatedDatetime());

            return ResponseDto.success(dto);
        } catch (Exception e) {
            log.error("게시글 조회 실패", e);
            return ResponseDto.fail("게시글 조회에 실패했습니다.");
        }
    }

    public ResponseDto<Void> update(BoardDto boardDto) {
        try {
            BoardEntity entity = boardRepository.findById(boardDto.getId())
                    .orElse(null);

            if (entity == null) {
                return ResponseDto.fail("게시글을 찾을 수 없습니다.");
            }

            if (boardDto.getTitle() != null) {
                entity.setTitle(boardDto.getTitle());
            }
            if (boardDto.getText() != null) {
                entity.setText(boardDto.getText());
            }
            if (boardDto.getType() != null) {
                entity.setType(boardDto.getType());
            }

            boardRepository.save(entity);
            return ResponseDto.success(null);
        } catch (Exception e) {
            log.error("게시글 수정 실패", e);
            return ResponseDto.fail("게시글 수정에 실패했습니다.");
        }
    }

    public ResponseDto<List<BoardDto>> list() {
        try {
            List<BoardEntity> entities = boardRepository.findAll();

            List<BoardDto> dtos = entities.stream().map(entity -> {
                BoardDto dto = new BoardDto();
                dto.setId(entity.getId());
                dto.setUserId(entity.getUserId());
                dto.setTitle(entity.getTitle());
                dto.setText(entity.getText());
                dto.setType(entity.getType());
                dto.setCreatedDatetime(entity.getCreatedDatetime());
                return dto;
            }).toList();

            return ResponseDto.success(dtos);
        } catch (Exception e) {
            log.error("게시글 목록 조회 실패", e);
            return ResponseDto.fail("게시글 목록 조회에 실패했습니다.");
        }
    }
}
