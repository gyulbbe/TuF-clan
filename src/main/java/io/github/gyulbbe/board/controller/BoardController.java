package io.github.gyulbbe.board.controller;

import io.github.gyulbbe.board.dto.BoardDto;
import io.github.gyulbbe.board.service.BoardService;
import io.github.gyulbbe.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/board")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<Void>> insert(@Valid @RequestBody BoardDto boardDto) {
        return ResponseEntity.ok(boardService.insert(boardDto));
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto<BoardDto>> get(@RequestBody BoardDto boardDto) {
        return ResponseEntity.ok(boardService.get(boardDto.getId()));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto<Void>> update(@Valid @RequestBody BoardDto boardDto) {
        return ResponseEntity.ok(boardService.update(boardDto));
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<BoardDto>>> list() {
        return ResponseEntity.ok(boardService.list());
    }
}
