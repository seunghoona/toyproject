package com.project.toyproject.board;

import com.project.toyproject.board.domain.BoardCreate;
import com.project.toyproject.board.infrastructure.QBoardCreateRepository;
import com.project.toyproject.config.TestConfig;
import net.jcip.annotations.Immutable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Rollback(value = false)
@Import(TestConfig.class)
class QBoardCreateRepositoryTest {

    @Autowired
    QBoardCreateRepository qBoardCreateRepository;


    @Test
    public void BoardCreateFindOne() throws Exception{
        //given
        BoardCreate oneBoardCreated = qBoardCreateRepository.findOneBoardCreated();


        System.out.println("oneBoardCreated = " + oneBoardCreated);
        //when
        
        //then
    }

}