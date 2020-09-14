package com.ykyk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @SequenceGenerator(name = "usersIdSeq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    private Integer id;
//    @NonNull
    private String name;
    private String fullName;
    private String inn;
    private LocalDate birthday;
    private String address;
    private String email;
    private String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToOne(targetEntity = Document.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "document_id")
    private Document document;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }



    @Override
    public String getUsername() {
        return name;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
