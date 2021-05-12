package rs.ac.uns.ftn.consumer.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.consumer.orderservice.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
