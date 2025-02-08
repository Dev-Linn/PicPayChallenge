package com.example.DesafioPicPay.services;

import com.example.DesafioPicPay.domain.user.User;
import com.example.DesafioPicPay.domain.user.UserType;
import com.example.DesafioPicPay.dtos.UserDTO;
import com.example.DesafioPicPay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validadeTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuario do tipo logista nao esta autorizado a relizar transação");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Usuario não tem saldo suficiente");
        }
    }

    public User findUserById(long id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
