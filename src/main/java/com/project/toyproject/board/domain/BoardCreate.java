package com.project.toyproject.board.domain;

import com.project.toyproject.common.domain.CreateModifyEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

@ToString(exclude = {"createBy","lastModifiedBy","createDate"})
public class BoardCreate extends CreateModifyEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "board_create_id")
    private Long id ;

    @Column(name = "board_name", length = 50)
    private String boardName;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BoardStatus boardStatus = BoardStatus.EMPTY;

    @Column(length = 255)
    private String description;

    @OneToMany(mappedBy = "boardCreate",cascade = CascadeType.ALL)
    @Builder.Default
    private List<Options> options = new ArrayList<>();

    @Builder
    public BoardCreate(Long id, String boardName, BoardStatus boardStatus, String description) {
        this.id = id;
        this.boardName = boardName;
        this.boardStatus = boardStatus;
        this.description = description;
    }

    public static class BoardCreateBuilder{
        public BoardCreateBuilder addOption(Options option) {

            BoardCreate.options.add(option);
            option.setBoardCreate();
            return this;
        }

    }

}
