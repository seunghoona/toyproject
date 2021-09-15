package com.project.toyproject.board.dto;

import com.project.toyproject.board.domain.BoardStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardCreateDTO {
    private Long boardId;
    private String name;
    private String boardStatus;
    private BoardStatus boardStatusEnum;
}
