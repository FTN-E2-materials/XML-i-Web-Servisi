package rs.ac.uns.ftn.kitchen.kitchenservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.kitchen.kitchenservice.domain.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
