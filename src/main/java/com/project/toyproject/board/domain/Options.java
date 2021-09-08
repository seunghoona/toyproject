package com.project.toyproject.board.domain;

import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.lang.annotation.Inherited;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "optionType")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "option")

public class Options {
    @Id
    @GeneratedValue
    @Column(name = "option_id")
    private Long id;

    @ManyToOne(fetch = LAZY,cascade = CascadeType.ALL )
    @JoinColumn(name = "board_create_id")
    @Setter
    private BoardCreate boardCreate;



}
