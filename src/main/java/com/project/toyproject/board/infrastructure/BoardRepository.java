package com.project.toyproject.board.infrastructure;

import com.project.toyproject.board.domain.BoardCreate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardCreate, Long> {
}
