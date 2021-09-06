package com.project.toyproject.board.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class BoardStatusTest {
    @Test
    public void findBoardStatus() throws Exception{
        //given
        BoardStatus stop = BoardStatus.valueOf("STOP");

        System.out.println("stop = " + stop);
        //when
        
        //then
    }

}