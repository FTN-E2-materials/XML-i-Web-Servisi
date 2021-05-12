package rs.ac.uns.ftn.kitchen.kitchenservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.kitchen.kitchenservice.domain.Ticket;
import rs.ac.uns.ftn.kitchen.kitchenservice.domain.TicketState;
import rs.ac.uns.ftn.kitchen.kitchenservice.dto.TicketLineItemsDTO;
import rs.ac.uns.ftn.kitchen.kitchenservice.dto.TicketResponseDTO;
import rs.ac.uns.ftn.kitchen.kitchenservice.service.TicketService;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class KitchenController {

    private final TicketService ticketService;

    public KitchenController(
            TicketService ticketService
    ) {
        this.ticketService = ticketService;
    }

    @GetMapping("/hello")
    public ResponseEntity<?> get() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        return new ResponseEntity<>(String.format("Hello from service with ip address %s!", ip), HttpStatus.OK);
    }

    @PostMapping("/verify/{restaurantId}")
    public ResponseEntity<?> verify(@PathVariable("restaurantId") Long restaurantId,
                                    @RequestBody TicketLineItemsDTO items) {
        return new ResponseEntity<>(this.ticketService.verify(restaurantId, items), HttpStatus.OK);
    }

    @PostMapping("/create/{restaurantId}/{orderId}")
    public ResponseEntity<?> create(@PathVariable("restaurantId") Long restaurantId,
                                    @PathVariable("orderId") Long orderId,
                                    @RequestBody TicketLineItemsDTO items) {
        this.ticketService.create(restaurantId, orderId, items);
        return new ResponseEntity<>("Successful ticket creation!", HttpStatus.OK);
    }

    @PutMapping("/update/{ticketId}/{status}")
    public ResponseEntity<?> create(@PathVariable("ticketId") Long ticketId,
                                    @PathVariable("status") TicketState ticketState) {
        Ticket ticket = this.ticketService.update(ticketId, ticketState);
        return new ResponseEntity<>(new TicketResponseDTO(ticket), HttpStatus.OK);
    }

}
