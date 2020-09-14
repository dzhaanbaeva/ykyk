package com.ykyk.repository;


import com.ykyk.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findById(int id);

    @Query("SELECT p FROM User p  WHERE (p.name like concat(:name, '%')) or (p.name like concat('%',:name,'%')) or (p.name like concat('%', :name)) or " +
            "(p.fullName like concat(:name, '%')) or (p.fullName like concat('%',:name,'%')) or (p.fullName like concat('%', :name))")
    Page<User> findAllByName(String name, Pageable pageable);
    Page<User> findUserByDocument_NumberOfDoc(int number, Pageable pageable);
    Page<User> findUserByBirthday(LocalDate date, Pageable pageable);
    boolean existsByInn(String inn);
    boolean existsByEmail(String email);

}
