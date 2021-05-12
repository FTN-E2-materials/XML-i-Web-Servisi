package rs.ac.uns.ftn.consumer.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.consumer.orderservice.domain.Order;
import rs.ac.uns.ftn.consumer.orderservice.domain.OrderStatus;
import rs.ac.uns.ftn.consumer.orderservice.dto.OrderRequestDTO;
import rs.ac.uns.ftn.consumer.orderservice.dto.OrderResponseDTO;
import rs.ac.uns.ftn.consumer.orderservice.service.OrderService;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @GetMapping("/hello")
    public ResponseEntity<?> get() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        return new ResponseEntity<>(String.format("Hello from service with ip address %s!", ip), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderRequestDTO orderRequestDTO) {
        Order order = this.orderService.createOrder(orderRequestDTO);
        return new ResponseEntity<>(new OrderResponseDTO(order), HttpStatus.OK);
    }

    @PutMapping("/update/{orderId}/{status}")
    public ResponseEntity<?> update(@PathVariable("orderId") Long orderId,
                                    @PathVariable("status") OrderStatus orderStatus) {
        this.orderService.update(orderId, orderStatus);
        return new ResponseEntity<>("Successful updated order", HttpStatus.OK);
    }

}
