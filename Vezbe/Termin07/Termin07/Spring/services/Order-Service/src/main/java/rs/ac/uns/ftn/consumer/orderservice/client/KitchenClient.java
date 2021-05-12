package rs.ac.uns.ftn.consumer.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.uns.ftn.consumer.orderservice.dto.OrderLineItemsDTO;

@FeignClient(name = "kitchen", url = "${app.kitchen.url}")
public interface KitchenClient {

    @PostMapping("/verify/{restaurantId}")
    boolean verify(@PathVariable("restaurantId") Long restaurantId,
                   @RequestBody OrderLineItemsDTO items);

    @PostMapping("/create/{restaurantId}/{orderId}")
    String create(@PathVariable("restaurantId") Long restaurantId,
                                    @PathVariable("orderId") Long orderId,
                                    @RequestBody OrderLineItemsDTO items);
}
