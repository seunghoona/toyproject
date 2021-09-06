package com.project.toyproject.board.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class File {
    @Column(name = "new_file_name")
    private String newfileName;

    @Column(name = "origin_file_name")
    private String originFileName;

    @Column(name = "file_size")
    private Long fileSize;



}
