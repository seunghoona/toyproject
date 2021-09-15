package com.project.toyproject.board.domain;

import com.project.toyproject.common.domain.CreateModifyEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class BoardCreate extends CreateModifyEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "board_create_id")
    private Long id;

    @Column(name = "board_name", length = 50)
    private String boardName;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BoardStatus boardStatus = BoardStatus.EMPTY;

    @Column(length = 255)
    private String description;

    @OneToMany(mappedBy = "boardCreate", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Options> options = new HashSet<>();

    @Builder
    public BoardCreate(Long id, String boardName, BoardStatus boardStatus, String description) {
        this.id = id;
        this.boardName = boardName;
        this.boardStatus = boardStatus;
        this.description = description;
    }




    public void addOption(Set<Options> options) {
        this.options = options;
        options.stream().forEach(s->s.setBoardCreate(this));
    }

    public void addOption(Options options) {
        this.options.add(options);
        options.setBoardCreate(this);
    }


}
