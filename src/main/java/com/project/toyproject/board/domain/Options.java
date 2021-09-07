package com.project.toyproject.board.domain;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
public class Options {
    @Id
    private Long id;

    /**
     *  @Embedded objects 들은 언제나 그들의 parents들의 lifecycle에 의해 manage된다.
     * 만약 parent가 update나 delete될 때, 그들 역시 그것을 따르게 된다.
     */
    @Embedded
    private OptionDate optionDate;


}
