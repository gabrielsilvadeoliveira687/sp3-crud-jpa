package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.Transaction;
<<<<<<< HEAD
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.repositories.TransctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.picpaysimplificado.domain.user.User;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
=======
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
>>>>>>> 466f858cd88770e766070a3703062ffb1bd5b9ef


@Service
public class TransactionService {
<<<<<<< HEAD
    @Autowired
    private UserService userService;

    @Autowired
    private TransctionRepository repository;

    @Autowired
    private RestTemplate restTemplate;
    public void createTransaction (TransactionDTO transaction) throws Exception {
       User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.recevicerId());

        boolean isAuthorized= this.authorizeTransction(sender, transaction.value());
        UserService.validateTransaction(sender, transaction.value());
        if (authorizeTransction(sender, transaction.value())){
            throw new Exception("Transaçao nao autorizada");
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount((transaction.value()));
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());


        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }
        public boolean authorizeTransction(User sender, BigDecimal value){
            ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
            if (authorizationResponse.getStatusCode()== HttpStatus.OK){
                 String message =(String) authorizationResponse.getBody().get("message");
                return "Autorizado".equalsIgnoreCase(message);
        }else return false;
    }
}
=======

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
>>>>>>> 466f858cd88770e766070a3703062ffb1bd5b9ef
