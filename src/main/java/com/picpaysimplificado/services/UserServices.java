package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.picpaysimplificado.domain.user.User;

import java.math.BigDecimal;

@Service
public class UserServices   {
   @Autowired
    private UserRepository repository;

   public void validadeteTransaction(User sender, BigDecimal ammount) throws Exception {
       if (sender.getUserType() == UserType.MERCHANT){
           throw new Exception("Usuario do logista nao esta autorizado a realizar transacao");

       }if(sender.getBalance().compareTo(ammount)<0){
           throw new Exception("Usuario nao tem saldo suficiente ");
       }
   }
   public User findUserById(Long Id) throws Exception{
       return this.repository.findUserById(Id).orElseThrow(()-> new Exception ("Usuario nao encontrado"));
   }
   public void saveUser(User user){
       this.repository.save(user);
   }
   }

