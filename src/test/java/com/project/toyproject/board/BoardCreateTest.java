package com.project.toyproject.board;

import com.project.toyproject.board.domain.BoardCreate;
import com.project.toyproject.board.infrastructure.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@DataJpaTest
@Rollback(value = false)
@ActiveProfiles(profiles = {"test"})
class BoardCreateTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시판 생성을 테스트")
    void crateTest() throws Exception {
        BoardCreate createNoticeBoard = BoardCreate.builder()
                .id(1L)
                .boardName("공지사항")
                .description("공지사항을 만드는 게시판입니다.")
                .build();

        boardRepository.save(createNoticeBoard);
    }
}