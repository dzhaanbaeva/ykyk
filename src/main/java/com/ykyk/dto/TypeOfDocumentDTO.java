package com.ykyk.dto;

import com.ykyk.model.TypeOfDocument;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@ToString
public class TypeOfDocumentDTO {
    private String name;
    private int id;

    public static TypeOfDocumentDTO from(TypeOfDocument type){
        return builder()
                .id(type.getId())
                .name(type.getName())
                .build();
    }
}
