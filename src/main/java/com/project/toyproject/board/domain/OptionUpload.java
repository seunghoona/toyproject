package com.project.toyproject.board.domain;

import javax.persistence.*;

@Embeddable
public class OptionUpload implements  OptionsInteface{

    @EmbeddedId
    private BoardCreateId id;

    @Embedded
    private File file;

}
