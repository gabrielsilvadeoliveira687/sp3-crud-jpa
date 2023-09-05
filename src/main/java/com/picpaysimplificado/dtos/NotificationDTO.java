package com.picpaysimplificado.dtos;

<<<<<<< HEAD
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.picpaysimplificado.domain.user.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;


public record NotificationDTO(String email, String message) {

=======
public record NotificationDTO (String email, String message ){
>>>>>>> 466f858cd88770e766070a3703062ffb1bd5b9ef
}
