package com.project.toyproject.board.domain;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class BoardCreateId implements Serializable {
    @Id
    private Long boardCreateId;
}
