package com.picpaysimplificado.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaysimplificado.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
<<<<<<< HEAD
		Optional<User>findUserByDocument(String document);
		Optional<User>findUserById(Long id);


=======
		Optional<User>	findUserByDocument(String document);
		Optional<User>	findUserById(Long id);
	
>>>>>>> 466f858cd88770e766070a3703062ffb1bd5b9ef
}
