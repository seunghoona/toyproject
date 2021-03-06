package com.project.toyproject.board.infrastructure;

import com.project.toyproject.board.domain.BoardCreate;
import com.project.toyproject.board.domain.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardCreate, Long> {
}
