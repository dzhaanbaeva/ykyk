package com.ykyk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "documents")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @SequenceGenerator(name = "documentsIdSeq", sequenceName = "documents_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documentsIdSeq")
    private Integer id;

    @Column(name = "number_of_doc")
    private int numberOfDoc;
    @Column(name = "issued_by_whom")
    private String issuedByWhom;
    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @ManyToOne(targetEntity = TypeOfDocument.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "type_id")
    private TypeOfDocument typeOfDocument;
}
