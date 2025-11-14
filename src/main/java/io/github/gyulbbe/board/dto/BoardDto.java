package io.github.gyulbbe.board.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDto {
    private Long id;
    private Long userId;
    private String title;
    private String text;
    private String type;
    private Date createdDatetime;
}