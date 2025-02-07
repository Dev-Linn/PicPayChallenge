package com.example.DesafioPicPay.repositories;

import com.example.DesafioPicPay.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //pode ou nao existir um usuario.
    Optional<User>findUserByDocument(String document);

    Optional<User>findUserById(Long id);

}
