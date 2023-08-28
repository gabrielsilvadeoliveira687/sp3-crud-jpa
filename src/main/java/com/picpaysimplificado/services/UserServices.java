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
    private UserRepository userRepository;

   public void validadeteTransaction(User sender, BigDecimal ammount) throws Exception {
       if (sender.getUserType() == UserType.MERCHANT){
           throw new Exception("Usuario do logista nao esta autorizado a realizar transacao");
       }
   }

}
