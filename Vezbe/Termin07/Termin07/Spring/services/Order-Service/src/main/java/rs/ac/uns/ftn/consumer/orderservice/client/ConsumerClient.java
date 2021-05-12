package rs.ac.uns.ftn.consumer.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "consumer", url = "${app.consumer.url}")
public interface ConsumerClient {

    @GetMapping("/verify/{consumerId}")
    boolean verify(@PathVariable("consumerId") Long consumerId);

}
