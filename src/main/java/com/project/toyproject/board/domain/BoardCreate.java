package com.project.toyproject.board.domain;

import com.project.toyproject.common.domain.CreateModifyEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
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



    public void addOption(Set<Options> options) {
        this.options = options;
        options.stream().forEach(s -> s.setBoardCreate(this));
    }

    public void addOption(Options options) {
        this.options = Set.of(options);
        options.setBoardCreate(this);
    }

 /*   public static BoardCreate.BoardCreateBuilder builders(){
        return new BoardCreate.BoardCreateBuilder(this);
    }


    static class BoardCreateBuilder {
        private Set<Options> options = new HashSet<>();
        public BoardCreateBuilder(){
            options.add

        }

        public BoardCreate.BoardCreateBuilder getTest(Set<Option>){
            this.options

            return this;
        }
    }*/


}
