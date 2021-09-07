package com.project.toyproject.board.domain;

import com.project.toyproject.common.domain.CreateModifyEntity;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class BoardCreate extends CreateModifyEntity{

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "board_create_id")
    private Long id ;

    @Column(name = "board_name", length = 50)
    private String boardName;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BoardStatus boardStatus = BoardStatus.STAND_BY;

    @Column(length = 255)
    private String description;




}
