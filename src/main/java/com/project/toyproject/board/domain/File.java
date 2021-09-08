package com.project.toyproject.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Builder
public class File {

    @Column(name = "file_upload_limit")
    private Long limit;

    @Column(name = "file_max")
    private Long max;

    @Column(name = "file_min")
    private Long min ;

}
