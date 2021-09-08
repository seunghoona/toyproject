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
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@CustomDataJpaTest
@Rollback(value = false)
class BoardCreateTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시판 생성을 테스트")
    void crateTest() throws Exception {

        BoardCreate 공지사항 = BoardCreate.builder()
                .boardName("공지사항")
                .description("공지사항을 만드는 게시판입니다.").build();

        OptionDefault optionDefault = OptionDefault.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDate.of(2021, 12, 31).atTime(0, 0))
                .boardCreate(공지사항)
                .build();

        OptionUpload test = OptionUpload.builder()
                .file(
                        File.builder()
                                .min(1000L)
                                .max(1000000L)
                                .limit(10L)
                                .build()
                )
                .boardCreate(공지사항)
                .build();

        List<Options> optionDefault1 = List.of(optionDefault, test);

        boardRepository.saveAll(optionDefault1);
    }
}