package com.project.toyproject.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardCreateSearchCondition {

    private String boardName;
    private String boardStatus;
    private String optionType;
    private String limit;
}
