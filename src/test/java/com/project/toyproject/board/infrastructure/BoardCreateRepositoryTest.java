package com.project.toyproject.board.infrastructure;

import com.project.toyproject.board.domain.BoardCreate;

import com.project.toyproject.board.dto.BoardCreateDTO;
import com.project.toyproject.board.dto.BoardCreateSearchCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BoardCreateRepositoryTest {


    @Autowired
    private EntityManager em;

    @Autowired
    private BoardCreateRepository boardCreateRepository;



    @Test
    public void basicTest() throws Exception{
        //given
        BoardCreate boardCreate = BoardCreate.builder()
                .boardName("친한친구")
                .build();
        boardCreateRepository.save(boardCreate);
        Optional<BoardCreate> findBoardCreate = boardCreateRepository.findById(boardCreate.getId());
        assertThat(boardCreate).isEqualTo(findBoardCreate.get());

        List<BoardCreate> findBoardCreateNames = boardCreateRepository.findByBoardName("친한친구");
        assertThat(findBoardCreateNames).containsExactly(boardCreate);


        List<BoardCreate> 친한친구 = boardCreateRepository.findByBoardName("친한친구");
        assertThat(findBoardCreateNames).containsExactly(boardCreate);

    }
    
    
    @Test
    public void basicQueryDSLTest() throws Exception{
        BoardCreate boardCreate = BoardCreate.builder()
                .boardName("친한친구")
                .build();
        boardCreateRepository.save(boardCreate);
        Optional<BoardCreate> findBoardCreate = boardCreateRepository.findById_Query_DSL(boardCreate.getId());
        assertThat(boardCreate).isEqualTo(findBoardCreate.get());

        List<BoardCreate> findBoardCreateNames = boardCreateRepository.findAll_QueryDSL();
        assertThat(findBoardCreateNames).containsExactly(boardCreate);


        List<BoardCreate> 친한친구 = boardCreateRepository.findByBoardName_QueryDSL("친한친구");
        assertThat(findBoardCreateNames).containsExactly(boardCreate);
    }

    @Test
    public void get() throws Exception{
        BoardCreate boardCreate = BoardCreate.builder()
                .boardName("친한친구")
                .build();

        BoardCreateSearchCondition condition = BoardCreateSearchCondition.builder()
                .boardName("친한친구").build();

        boardCreateRepository.save(boardCreate);
        List<BoardCreateDTO> byBoardCreateOptions = boardCreateRepository.findByBoardCreateOptions(condition);
        assertThat(byBoardCreateOptions).extracting("name").contains("친한친구");

    }

}