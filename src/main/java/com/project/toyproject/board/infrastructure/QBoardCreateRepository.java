package com.project.toyproject.board.infrastructure;

import com.project.toyproject.board.domain.BoardCreate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QBoardCreateRepository {

    private final JPAQueryFactory jpaQueryFactory;


    public Optional<BoardCreate> findOneBoardCreated(){
        return Optional.empty();
    }
}
