package rs.ac.uns.ftn.consumer.consumerservice.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.consumer.consumerservice.domain.Consumer;
import rs.ac.uns.ftn.consumer.consumerservice.dto.ConsumerRequestDTO;
import rs.ac.uns.ftn.consumer.consumerservice.exceptions.NotFoundException;
import rs.ac.uns.ftn.consumer.consumerservice.repository.ConsumerRepository;

@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public ConsumerService(
            ConsumerRepository consumerRepository
    ) {
        this.consumerRepository = consumerRepository;
    }

    public Consumer createConsumer(ConsumerRequestDTO consumer) {
        return this.consumerRepository.save(new Consumer(consumer));
    }

    public boolean verify(Long consumerId) {
        if (!this.consumerRepository.existsById(consumerId)) {
            throw new NotFoundException("Consumer with that id does not exist!");
        }

        return true;
    }

}
