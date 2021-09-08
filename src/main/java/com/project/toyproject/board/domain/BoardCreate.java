package com.project.toyproject.board.domain;

import com.project.toyproject.common.domain.CreateModifyEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
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

    @OneToMany(mappedBy = "boardCreate")
    private Set<Options> options = new HashSet<>();


}
