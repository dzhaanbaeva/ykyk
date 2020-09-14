package com.ykyk.repository;


import com.ykyk.model.TypeOfDocument;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TypeOfDocumentRepository extends JpaRepository<TypeOfDocument, Integer> {

TypeOfDocument findByName(String name);
TypeOfDocument findById(int id);

}
