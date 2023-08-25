package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.Transaction;
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


@Service
public class TransactionService {
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