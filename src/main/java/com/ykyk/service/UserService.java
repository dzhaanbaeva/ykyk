package com.ykyk.service;

import com.ykyk.dto.UserDTO;
import com.ykyk.model.Document;
import com.ykyk.model.TypeOfDocument;
import com.ykyk.model.User;
import com.ykyk.repository.DocumentRepository;
import com.ykyk.repository.TypeOfDocumentRepository;
import com.ykyk.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

   private final UserRepository userRepository;
    private final TypeOfDocumentRepository typeOfDocumentRepository;
    private final DocumentRepository documentRepository;

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::from).collect(Collectors.toList());
    }

    public Page<UserDTO> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::from);
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public String createUser(String name, String fullName, String inn, String birthday, String address, String email,
                           int documentId, int numberOfDoc, String issuedByWhom, String dateOfIssue, String expirationDate) {
        if (userRepository.existsByInn(inn)) {
            return "/errors/error-already-have";
        }else if(userRepository.existsByEmail(email)){
            return "/errors/error-email-already-have";
        }
        else {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate localDateBirthday = LocalDate.parse(birthday, formatter);
            LocalDate localDateIssueOf = LocalDate.parse(dateOfIssue, formatter);
            LocalDate localDateExpiration = LocalDate.parse(expirationDate, formatter);

            TypeOfDocument typeDocument = typeOfDocumentRepository.findById(documentId);
            var document = Document.builder()
                    .numberOfDoc(numberOfDoc)
                    .issuedByWhom(issuedByWhom)
                    .dateOfIssue(localDateIssueOf)
                    .expirationDate(localDateExpiration)
                    .typeOfDocument(typeDocument)
                    .build();
            documentRepository.save(document);

            var user = User.builder()
                    .name(name)
                    .fullName(fullName)
                    .inn(inn)
                    .birthday(localDateBirthday)
                    .address(address)
                    .email(email)
                    .document(document)
                    .build();
            userRepository.save(user);

            return "redirect:/users";
        }
    }

    public Page<UserDTO> getUserSearch(String text, Pageable pageable) {
        return userRepository.findAllByName(text, pageable)
                .map(UserDTO::from);
    }
    public Page<UserDTO> getUserNumber(int number, Pageable pageable) {
        return userRepository.findUserByDocument_NumberOfDoc(number, pageable)
                .map(UserDTO::from);
    }
    public Page<UserDTO> getUserBirthdayDate(LocalDate date, Pageable pageable) {
        return userRepository.findUserByBirthday(date, pageable)
                .map(UserDTO::from);
    }
}
