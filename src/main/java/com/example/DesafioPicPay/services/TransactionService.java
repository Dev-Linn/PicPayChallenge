package com.example.DesafioPicPay.services;

import com.example.DesafioPicPay.domain.transaction.Transaction;
import com.example.DesafioPicPay.domain.user.User;
import com.example.DesafioPicPay.dtos.TransactionDTO;
import com.example.DesafioPicPay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO transaction) throws Exception{
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validadeTransaction(sender, transaction.value());

        boolean isAuth = this.authenticateTransaction(sender, transaction.value());
        if(!isAuth) {
            throw new Exception("Transacao invalida");
        }

        Transaction transacao = new Transaction();
        transacao.setAmount(transaction.value());
        transacao.setSender(sender);
        transacao.setReceiver(receiver);
        transacao.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transacao.getAmount()));
        receiver.setBalance(receiver.getBalance().add(transacao.getAmount()));

        this.transactionRepository.save(transacao);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transacao realizada com sucesso");
        this.notificationService.sendNotification(receiver, "Transacao recebida com sucesso");

        return transacao;

    }

    public Boolean authenticateTransaction(User sender, BigDecimal value) {
      ResponseEntity<Map> authResponse = restTemplate
              .getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

      if (authResponse.getStatusCode() == HttpStatus.OK) {
          String message = (String) authResponse.getBody().get("status");
          return "success".equalsIgnoreCase(message);
      } else return false;
    }

}
