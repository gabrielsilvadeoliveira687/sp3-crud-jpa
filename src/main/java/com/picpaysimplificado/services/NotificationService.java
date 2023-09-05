package com.picpaysimplificado.services;

<<<<<<< HEAD
import com.picpaysimplificado.domain.user.User;
=======
>>>>>>> 466f858cd88770e766070a3703062ffb1bd5b9ef
import com.picpaysimplificado.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.client.RestTemplate;

=======
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.picpaysimplificado.domain.user.User;
import org.springframework.http.HttpStatusCode;

@Service
<<<<<<< HEAD
public class  NotificationService {
=======
>>>>>>> 466f858cd88770e766070a3703062ffb1bd5b9ef
public class NotificationService {
>>>>>>> 8824fbf98ccb09df634d9db59e156b6c75ee2b21
    @Autowired
    private RestTemplate restTemplate;

<<<<<<< HEAD
    public void  sendNotification(User user, String message) throws Exception {
        String email=user.getEmail();
        NotificationDTO notificationRequest= new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse= restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.OK));
        System.out.println("erro ao enviar notificacao");
        throw new Exception("Servico de notificacao esta fora do ar");

=======
    public void sendNotification(User user, String message) throws Exception{
        String email= user.getEmail();
        NotificationDTO notificationRequest= new NotificationDTO(email, message);
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);


        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            throw new Exception("Servico de notificacao esta away");
        }
>>>>>>> 466f858cd88770e766070a3703062ffb1bd5b9ef
    }
}
