package com.project.toyproject.jpqlExample;

import com.project.toyproject.board.domain.*;
import com.project.toyproject.config.CustomDataJpaTest;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

import static com.project.toyproject.board.domain.QBoardCreate.boardCreate;
import static com.project.toyproject.board.domain.QOptionUpload.optionUpload;
import static com.project.toyproject.board.domain.QOptions.options;


@CustomDataJpaTest
@Rollback(value = false)
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
    public void pagin1() throws Exception {
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
    public void aggregate() throws Exception {
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

    public void groupby() throws Exception {
        List<BoardStatus> fetch1 = queryFactory
                .select(boardCreate.boardStatus)
                .from(boardCreate)
                .leftJoin(boardCreate.options, options)
                .groupBy(boardCreate.boardStatus)
                .fetch();

        System.out.println("fetch1 = " + fetch1);
    }


    @Test
    public void join() throws Exception {
        //given
        List<Tuple> fetch = queryFactory
                .select(boardCreate, optionUpload)
                .from(boardCreate)
                .join(boardCreate.options, optionUpload._super)
                .where(optionUpload._super.id.eq(14L)).fetch();
        //when
        fetch.stream().forEach(System.out::println);
        //then
    }


    @Test
    public void theta_join() throws Exception {
        BoardCreate movieboard = BoardCreate.builder()
                .boardName("동영상게시판")
                .description("공지사항을 만드는 게시판입니다.")
                .build();
        BoardCreate imgBoard = BoardCreate.builder()
                .boardName("이미지게시판")
                .description("공지사항을 만드는 게시판입니다.")
                .build();

        entityManager.persist(imgBoard);
        entityManager.persist(movieboard);

        List<BoardCreate> fetch = queryFactory
                .selectFrom(boardCreate)
                .from(boardCreate, options)
                .where(boardCreate.boardName.like("동영상게시판%")
                        .and(boardCreate.boardName.like("이미지게시판%"))
                )
                .fetch();

        Assertions.assertThat(fetch)
                .extracting("boardName")
                .containsExactly("동영상게시판", "이미지게시판");
    }


    @Test
    public void joinOn() throws Exception {
        BoardCreate movieboard = BoardCreate.builder()
                .boardName("동영상게시판")
                .description("공지사항을 만드는 게시판입니다.")
                .build();
        BoardCreate imgBoard = BoardCreate.builder()
                .boardName("이미지게시판")
                .description("공지사항을 만드는 게시판입니다.")
                .build();
        movieboard.addOption(OptionUpload.builder()
                .file(File.builder()
                        .min(10L)
                        .max(20L)
                        .limit(30L)
                        .build())
                .build());
        entityManager.persist(imgBoard);
        entityManager.persist(movieboard);

        List<Tuple> list = queryFactory.
                select(boardCreate, optionUpload)
                .from(boardCreate)
                .leftJoin(boardCreate.options, optionUpload._super)
                .on(boardCreate.boardName.eq("동영상게시판")).fetch();

    }
}
