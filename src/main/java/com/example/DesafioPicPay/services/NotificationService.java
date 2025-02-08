package com.example.DesafioPicPay.services;

import com.example.DesafioPicPay.domain.user.User;
import com.example.DesafioPicPay.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String msg) throws Exception {
        String email = user.getEmail();

        NotificationDTO notificationDTO = new NotificationDTO(msg, email);

     /* ResponseEntity<String> notificationResponse =  restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationDTO, String.class );


      if(!notificationResponse.getStatusCode().equals(HttpStatus.OK)) {
          System.out.println("Erro ao enviar notificao");
          throw new Exception("Servico de notificao esta fora do ar");
      }

      */


        System.out.println("Notificacao enviada para o usuario");
    }

}
