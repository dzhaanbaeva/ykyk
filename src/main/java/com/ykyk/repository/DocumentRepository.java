package com.ykyk.repository;


import com.ykyk.model.Document;

import org.springframework.data.jpa.repository.JpaRepository;



public interface DocumentRepository extends JpaRepository<Document, Integer> {
Document findDocumentByTypeOfDocumentId(int typeOfDocument);

}
