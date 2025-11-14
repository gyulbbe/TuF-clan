package io.github.gyulbbe.commentary.controller;

import io.github.gyulbbe.commentary.dto.CommentaryDto;
import io.github.gyulbbe.commentary.service.CommentaryService;
import io.github.gyulbbe.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/commentary")
@RequiredArgsConstructor
@RestController
public class CommentaryController {

    private final CommentaryService commentaryService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<Void>> insertCommentary(@Valid @RequestBody CommentaryDto commentaryDto) {
        return ResponseEntity.ok(commentaryService.insertCommentary(commentaryDto));
    }

    @PostMapping("/embed-all")
    public ResponseEntity<ResponseDto<String>> embedAllCommentaries() {
        return ResponseEntity.ok(commentaryService.embedAllCommentaries());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto<CommentaryDto>> findCommentaryById(@RequestBody CommentaryDto commentaryDto) {
        return ResponseEntity.ok(commentaryService.findCommentaryById(commentaryDto.getId()));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto<Void>> updateCommentary(@Valid @RequestBody CommentaryDto commentaryDto) {
        return ResponseEntity.ok(commentaryService.updateCommentary(commentaryDto.getId(), commentaryDto));
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<CommentaryDto>>> list() {
        return ResponseEntity.ok(commentaryService.list());
    }
}
