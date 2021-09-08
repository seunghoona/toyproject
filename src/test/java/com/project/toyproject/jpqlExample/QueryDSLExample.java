package com.project.toyproject.jpqlExample;

import com.project.toyproject.board.domain.BoardCreate;
import com.project.toyproject.board.domain.QBoardCreate;
import com.project.toyproject.board.domain.QOptions;
import com.project.toyproject.config.CustomDataJpaTest;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.project.toyproject.board.domain.QBoardCreate.boardCreate;
import static com.project.toyproject.board.domain.QOptions.options;


@CustomDataJpaTest
public class QueryDSLExample {

    @Autowired
    JPAQueryFactory queryFactory;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("페이징하는 방법 ")
    public void resultFetchTest() throws Exception {


        //데이터를 가져오는 쿼리랑 토탈이 다른 경우에는 별로도로 사용해서 가져와야한다.
        QueryResults<BoardCreate> boardCreateQueryResults = queryFactory
                .selectFrom(boardCreate)
                .fetchResults();

        List<BoardCreate> results = boardCreateQueryResults.getResults();
        long total = boardCreateQueryResults.getTotal();


        //카운트만 가져오는 거
        long l = queryFactory
                .selectFrom(boardCreate)
                .fetchCount();
    }

    @Test
    @DisplayName("정렬하는 방법")
    /**
     * 1. 게시판
     *
     *
     * */
    public void sort() throws Exception {

        entityManager.persist(BoardCreate.builder()
                .boardName("자유게시판")
                .description("자유게시판을 생성합니다.")
                .build());

        entityManager.persist(BoardCreate.builder()
                .boardName("사진게시판")
                .description("사진게시판을 생성합니다.")
                .build());

        entityManager.persist(BoardCreate.builder()
                .boardName(null)
                .description("사진게시판을 생성합니다.")
                .build());


        List<BoardCreate> boardCreates = queryFactory
                .selectFrom(boardCreate)
                .orderBy(boardCreate.boardName.desc(),
                        boardCreate.boardName.asc().nullsLast()
                ).fetch();

        boardCreates.stream().forEach(System.out::println);
    }

    
    @Test
    public void pagin1() throws Exception{
        entityManager.persist(BoardCreate.builder()
                .boardName(null)
                .description("사진게시판을 생성합니다.")
                .build());
        QueryResults<BoardCreate> boardCreateQueryResults = queryFactory
                .selectFrom(boardCreate)
                .orderBy(boardCreate.boardName.desc())
                .offset(1)
                .limit(2)
                .fetchResults();

        Assertions.assertThat(boardCreateQueryResults.getTotal()).isEqualTo(1);
    }

    @Test
    public void aggregate() throws Exception{
        entityManager.persist(BoardCreate.builder()
                .boardName(null)
                .description("사진게시판을 생성합니다.")
                .build());
        List<Tuple> fetch = queryFactory
                .select(
                        boardCreate.count(),
                        boardCreate.id.max()
                ).from(boardCreate)
                .fetch();

        Tuple tuple = fetch.get(0);

        Assertions.assertThat(tuple.get(boardCreate.count())).isEqualTo(1);
        /*Assertions.assertThat(tuple.get(boardCreate.id.max())).isEqualTo();*/

    }


    @Test
    public void groupby() throws Exception{
        List<BoardCreate> fetch = queryFactory
                .selectFrom(boardCreate)
                .leftJoin(options)
                .groupBy(boardCreate.boardStatus)
                .fetch();
    }

}
