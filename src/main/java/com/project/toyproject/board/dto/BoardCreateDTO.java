package com.project.toyproject.board.dto;

import com.project.toyproject.board.domain.BoardStatus;
import com.project.toyproject.board.domain.Options;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class BoardCreateDTO {
    private Long boardId;
    private String name;
    private String boardStatus;
    private BoardStatus boardStatusEnum;
    private String optionType;
    private int count;
    private Set<Options> options;

    @QueryProjection
    public BoardCreateDTO(Long boardId, String name, String boardStatus, BoardStatus boardStatusEnum, int count) {
        this.boardId = boardId;
        this.name = name;
        this.boardStatus = boardStatus;
        this.boardStatusEnum = boardStatusEnum;
        this.count = count;
    }

    @QueryProjection
    public BoardCreateDTO(Long boardId, String name, String boardStatus, Set<Options> options) {
        this.boardId = boardId;
        this.name = name;
        this.boardStatus = boardStatus;
        this.options = options;
    }
}
