@AnyMetaDef(name= "OptionMetaDef", metaType = "string", idType = "int",
        metaValues = {
                @MetaValue(value = "B", targetEntity = OptionUpload.class),
                @MetaValue(value = "G", targetEntity = OptionDate.class)
        }
)

@AnyMetaDef(name= "StudentMetaDef", metaType = "string", idType = "int",
        metaValues = {
                @MetaValue(value = "B", targetEntity = Boy.class),
        }
)

package com.project.toyproject.board.domain;

import com.project.toyproject.board.student.Boy;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;