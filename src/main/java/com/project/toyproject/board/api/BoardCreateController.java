package com.project.toyproject.board.api;

import com.project.toyproject.board.domain.BoardCreate;
import com.project.toyproject.board.infrastructure.QBoardCreateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardCreateController {

    private final QBoardCreateRepository qBoardCreateRepository;

    @GetMapping("create")
    public ResponseEntity<BoardCreate> boardCreate(){

        BoardCreate oneBoardCreated = qBoardCreateRepository.findOneBoardCreated();
        return ResponseEntity.ok(oneBoardCreated);
    }
}
