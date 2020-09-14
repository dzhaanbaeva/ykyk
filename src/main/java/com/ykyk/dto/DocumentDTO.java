package com.ykyk.dto;

import com.ykyk.model.Document;
import lombok.*;



import java.time.format.DateTimeFormatter;
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@ToString
public class DocumentDTO {

    private int id;
    private int numberOfDoc;
    private String issuedByWhom;
    private String dateOfIssue;
    private String expirationDate;
    private TypeOfDocumentDTO typeOfDocument;

    public  static DocumentDTO from (Document document){
        return builder()
                .numberOfDoc(document.getNumberOfDoc())
                .issuedByWhom(document.getIssuedByWhom())
                .dateOfIssue(document.getDateOfIssue().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .expirationDate(document.getExpirationDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .typeOfDocument(TypeOfDocumentDTO.from(document.getTypeOfDocument()))
                .build();
    }

}
