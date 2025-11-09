package io.github.gyulbbe.commentary.mapper;

import io.github.gyulbbe.commentary.dto.CommentaryDto;
import io.github.gyulbbe.commentary.dto.insertCommentaryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentaryMapper {
    int insertCommentary(insertCommentaryDto insertCommentaryDto);
    List<CommentaryDto> findAllCommentaries();
}
