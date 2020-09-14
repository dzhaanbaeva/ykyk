package com.ykyk.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@ToString
@Table(name = "type_of_document")
public class TypeOfDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 128)
    private String name;

}
