package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dtos.UserDto;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.picpaysimplificado.domain.user.User;
import java.math.BigDecimal;
import java.util.List;

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

   public User createUser(UserDto data){
    User newUser= new User(data);
    this.saveUser(newUser);
    return newUser;
   }

   public List<User> getAllUsers(){
       return this.repository.findAll();
   }
   public void saveUser(User user){
       this.repository.save(user);
   }
   }

