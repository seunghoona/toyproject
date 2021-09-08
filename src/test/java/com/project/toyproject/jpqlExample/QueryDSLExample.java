package com.project.toyproject.jpqlExample;

import com.project.toyproject.board.domain.BoardCreate;
import com.project.toyproject.board.domain.QBoardCreate;
import com.project.toyproject.config.CustomDataJpaTest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static com.project.toyproject.board.domain.QBoardCreate.boardCreate;

@CustomDataJpaTest
public class QueryDSLExample {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    public void resultFetchTest() throws Exception{

        //given
        BoardCreate boardCreate = jpaQueryFactory.
                selectFrom(QBoardCreate.boardCreate)
                .fetchOne();
        System.out.println("boardCreate = " + boardCreate);
        //when

        //then
    }
}
