package io.github.gyulbbe.map.controller;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.map.dto.insertMapDto;
import io.github.gyulbbe.map.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/map")
@RequiredArgsConstructor
@RestController
public class MapController {

    private final MapService mapService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<Void>> insertMap(@Valid @RequestBody insertMapDto insertMapDto) {
        return ResponseEntity.ok(mapService.insertMap(insertMapDto));
    }

    @GetMapping("/test")
    public void test() {
        System.out.println("테스트");
    }
}
