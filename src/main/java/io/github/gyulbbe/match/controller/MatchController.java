package io.github.gyulbbe.match.controller;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.match.dto.insertMatchInfoDto;
import io.github.gyulbbe.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/match")
@RequiredArgsConstructor
@RestController
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<Void>> insertMatchInfo(@Valid @RequestBody insertMatchInfoDto insertMatchInfoDto) {
        return ResponseEntity.ok(matchService.insertMatchInfo(insertMatchInfoDto));
    }

    @GetMapping("/test")
    public void test() {
        System.out.println("테스트");
    }
}
