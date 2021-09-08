package com.project.toyproject.board.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.swing.text.html.Option;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@DiscriminatorValue("O_DEFAULT")
@NoArgsConstructor(access = PROTECTED)
public class OptionDefault extends Options {

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "posted_date") //게시일자
    private LocalDateTime postedDate;

    @Builder
    public OptionDefault(Long id, BoardCreate boardCreate, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime postedDate) {
        super(id, boardCreate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.postedDate = postedDate;
    }
}
