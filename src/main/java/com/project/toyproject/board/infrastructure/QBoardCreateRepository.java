package com.project.toyproject.board.infrastructure;

import com.project.toyproject.board.domain.BoardCreate;

public interface QBoardCreateRepository  {
    public BoardCreate findOneBoardCreated();
}
