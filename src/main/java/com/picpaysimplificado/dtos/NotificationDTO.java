package com.picpaysimplificado.dtos;

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

}
