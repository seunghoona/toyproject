package com.project.toyproject.board;

import com.project.toyproject.board.domain.*;
import com.project.toyproject.board.infrastructure.BoardRepository;
import com.project.toyproject.config.CustomDataJpaTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@CustomDataJpaTest
@Rollback(value = false)
class BoardCreateTest {

    @Autowired
    private BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    @DisplayName("게시판 생성을 테스트")
    void crateTest() throws Exception {

        //날짜 옵션
        OptionDefault optionDefault = OptionDefault.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDate.of(2021, 12, 31).atTime(0, 0))
                .build();
        //파일 옵션
        OptionUpload test = OptionUpload.builder()
                .file(
                        File.builder()
                                .min(1000L)
                                .max(1000000L)
                                .limit(10L)
                                .build()
                )
                .build();

        BoardCreate notice = BoardCreate.builder()
                .boardName("공지사항")
                .description("공지사항을 만드는 게시판입니다.")
                .build();
        notice.addOption(optionDefault);
        notice.addOption(test);

        boardRepository.save(notice);

    }
}