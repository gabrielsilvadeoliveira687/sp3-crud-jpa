package com.picpaysimplificado.services;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import java.util.Optional;

  
@Service 
public class UserService {
	
	@Autowired
	private UserService repository;
	
			public static void validateTransaction(User sender, BigDecimal amount)throws Exception {
			if(sender.getUserType() == UserType.MERCHANT){
				throw new Exception("Usuário do tipo Lojista não está autorizado a realizar transação");
		} 
			if(sender.getBalance().compareTo(amount) < 0){
			throw new Exception("Saldo insuficiente");
				}
			}

	public User findUserById(Long id) throws Exception{
		Optional optional = null;
			return this.repository.findUserById(id).orElseThrow(() -> new Exception("user nao encotnroado"));
		}
	public void saveUser(User user){
				this.repository.saveUser(user);
		 }
}

