package com.project.toyproject.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;


@Entity
@DiscriminatorValue("O_UPLOAD")
@NoArgsConstructor(access = PROTECTED)
public class OptionUpload extends Options {

    @Embedded
    private File file;

    @Builder
    public OptionUpload(Long id, BoardCreate boardCreate, File file) {
        super(id, boardCreate);
        this.file = file;
    }

}

