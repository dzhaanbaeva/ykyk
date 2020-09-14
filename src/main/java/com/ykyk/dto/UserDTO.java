package com.ykyk.dto;





import com.ykyk.model.User;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@ToString
public class UserDTO {
    private int id;
    private String name;
    private String fullName;
    private String inn;
    private String birthday;
    private  String address;
    private String email;
    private String password;
    private DocumentDTO document;

    public static UserDTO from(User user){
        return builder()
                .id(user.getId())
                .name(user.getName())
                .fullName(user.getFullName())
                .inn(user.getInn())
                .birthday(user.getBirthday().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .address(user.getAddress())
                .email(user.getEmail())
                .password(user.getPassword())
                .document(DocumentDTO.from(user.getDocument()))
                .build();
    }
}
