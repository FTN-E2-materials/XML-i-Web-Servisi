package rs.ac.uns.ftn.consumer.consumerservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.consumer.consumerservice.domain.Consumer;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends CrudRepository<Consumer, Long> {

    boolean existsById(Long consumerId);

}
