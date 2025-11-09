package io.github.gyulbbe.chat.controller;

import io.github.gyulbbe.chat.dto.RequestChatDto;
import io.github.gyulbbe.chat.service.ChatService;
import io.github.gyulbbe.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat")
    public ResponseEntity<ResponseDto<String>> chat(@Valid @RequestBody RequestChatDto requestChatDto) {
        return ResponseEntity.ok(ResponseDto.success(chatService.chat(requestChatDto)));
    }
}
