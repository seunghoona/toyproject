package com.project.toyproject.board.domain;

import lombok.*;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoardCreate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "board_create_id")
    private Long id ;

    @Column(name = "board_name", length = 50)
    private String boardName;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BoardStatus boardStatus = BoardStatus.STAND_BY;

    @Column(length = 255)
    private String description;

    @ManyToAny(
            metaDef = "OptionsInteface",
            metaColumn = @Column(name = "options_kind"),
            fetch = FetchType.LAZY
    )
    private List<OptionsInteface> options = new ArrayList<>();


}
