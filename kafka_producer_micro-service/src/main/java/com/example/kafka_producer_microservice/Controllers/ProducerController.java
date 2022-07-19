package com.example.kafka_producer_microservice.Controllers;

import com.example.kafka_producer_microservice.Models.User;
import com.example.kafka_producer_microservice.Services.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    private final SenderService senderService;

    @Autowired
    public ProducerController(SenderService senderService) {
        this.senderService = senderService;
    }

    /*@PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message){
        senderService.send(message);
        return ResponseEntity.ok("OK");
    }*/

    @PostMapping("/send/user")
    public ResponseEntity<String> sendMessage(@RequestBody User user){
        senderService.sendUser(user);
        return ResponseEntity.ok("OK");
    }


}
