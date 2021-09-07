package com.project.toyproject.board.infrastructure;

import com.project.toyproject.board.domain.BoardCreate;
import com.project.toyproject.board.domain.QBoardCreate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.project.toyproject.board.domain.QBoardCreate.boardCreate;

@Repository
@RequiredArgsConstructor
public class QBoardCreateRepositoryImpl implements QBoardCreateRepository {

    public BoardCreate findOneBoardCreated() {

        return BoardCreate.builder().build();
    }
}
