package com.project.toyproject.board.infrastructure;

import com.project.toyproject.board.domain.BoardCreate;
import com.project.toyproject.board.domain.QOptionUpload;
import com.project.toyproject.board.dto.BoardCreateDTO;
import com.project.toyproject.board.dto.BoardCreateSearchCondition;
import com.project.toyproject.board.dto.QBoardCreateDTO;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.project.toyproject.board.domain.QBoardCreate.boardCreate;
import static com.project.toyproject.board.domain.QOptionUpload.optionUpload;

@Repository
@RequiredArgsConstructor
public class BoardCreateRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(BoardCreate boardCreate){
        em.persist(boardCreate);
    }

    public Optional<BoardCreate> findById(Long id){
        BoardCreate findBoardCreate = em.find(BoardCreate.class, id);
        return Optional.ofNullable(findBoardCreate);
    }

    public List<BoardCreate> findAll(){
        return em.createQuery("SELECT bc FROM BoardCreate bc",BoardCreate.class).getResultList();
    }

    public List<BoardCreate> findByBoardName(String boardName){
        return em.createQuery("SElECT bc FROM BoardCreate bc", BoardCreate.class).getResultList();
    }


    public List<BoardCreate> findAll_QueryDSL(){
        return queryFactory
                .selectFrom(boardCreate)
                .fetch();
    }

    public List<BoardCreate> findByBoardName_QueryDSL(String boardCreateName){
        return queryFactory
                .selectFrom(boardCreate)
                .where(boardCreateNameEq(boardCreateName))
                .fetch();
    }

    private Predicate boardCreateNameEq(String boardCreateName) {
        return boardCreateName != null ? boardCreate.boardName.eq(boardCreateName) : null;
    }

    public Optional<BoardCreate> findById_Query_DSL(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(boardCreate)
                .where(boardCreateIdEq(id))
                .fetchOne());
    }

    private Predicate boardCreateIdEq(Long id) {
        return id != null ? boardCreate.id.eq(id) : null;
    }


    public List<BoardCreateDTO> findByBoardCreateOptions(BoardCreateSearchCondition Boarcondition){
        return queryFactory
                .select(new QBoardCreateDTO(
                          boardCreate.id.as("boardId")
                        , boardCreate.boardName.as("name")
                        , boardCreate.boardStatus.stringValue().as("boardStatus")
                        , boardCreate.options.as("options")
                ))
                .from(boardCreate)
                .join(boardCreate.options, optionUpload._super)
                .fetch();
    }
}
