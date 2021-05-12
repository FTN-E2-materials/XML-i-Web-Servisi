package rs.ac.uns.ftn.kitchen.kitchenservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.uns.ftn.kitchen.kitchenservice.domain.TicketState;

@FeignClient(name = "order", url = "${app.order.url}")
public interface OrderClient {

    @PutMapping("/update/{orderId}/{status}")
    String update(@PathVariable("orderId") Long orderId,
                  @PathVariable("status") TicketState ticketState);

}
