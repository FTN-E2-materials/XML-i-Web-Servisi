package rs.ac.uns.ftn.consumer.consumerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.consumer.consumerservice.domain.Consumer;
import rs.ac.uns.ftn.consumer.consumerservice.dto.ConsumerRequestDTO;
import rs.ac.uns.ftn.consumer.consumerservice.dto.ConsumerResponseDTO;
import rs.ac.uns.ftn.consumer.consumerservice.service.ConsumerService;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class ConsumerController {

    private final ConsumerService consumerService;

    @Autowired
    public ConsumerController (
            ConsumerService consumerService
    ) {
        this.consumerService = consumerService;
    }

    @GetMapping("/hello")
    public ResponseEntity<?> get() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        return new ResponseEntity<>(String.format("Hello from service with ip address %s!", ip), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ConsumerRequestDTO consumerDTO) {
        Consumer consumer = this.consumerService.createConsumer(consumerDTO);
        return new ResponseEntity<>(new ConsumerResponseDTO(consumer), HttpStatus.OK);
    }

    @GetMapping("/verify/{consumerId}")
    public ResponseEntity<?> verify(@PathVariable("consumerId") Long consumerId) {
        System.out.println("Verification invoked!");
        return new ResponseEntity<>(this.consumerService.verify(consumerId), HttpStatus.OK);
    }

}
