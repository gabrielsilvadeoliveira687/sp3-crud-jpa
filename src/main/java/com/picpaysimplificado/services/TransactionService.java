package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.domain.user.User;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;


@Service
public class TransactionService {

    @Autowired
    private UserServices userService;
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO transaction) throws  Exception{
        User sender = this.userService.findUserById(transaction.senderId());
        User reciver = this.userService.findUserById(transaction.reciverId());

        userService.validadeteTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizateTransaction(sender, transaction.value());
        if(!this.authorizateTransaction( sender , transaction.value())){
            throw new Exception("Transacao nao Autorizada ");
        }
        Transaction newtransaction=  new Transaction();
        newtransaction.setAmount(transaction.value());
        newtransaction.setSender(sender);
        newtransaction.setReceiver(reciver);
        newtransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        reciver.setBalance(sender.getBalance().add(transaction.value()));

        this.repository.save(newtransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(reciver);

        this.notificationService.sendNotification(sender, "transacoes realizada com sucesso");
        this.notificationService.sendNotification(reciver, "transacoes realizada com sucesso");

return newtransaction;
    }

    public boolean authorizateTransaction(User sender, BigDecimal value ){
      ResponseEntity<Map> authorizateTransaction =restTemplate.getForEntity("https:/ /run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",Map.class);

        if (authorizateTransaction.getStatusCode()== HttpStatus.OK){
            String message  =(String) authorizateTransaction.getBody().get("message");

         return "Autorizado".equalsIgnoreCase(message);
        }else return false;



    }
}
