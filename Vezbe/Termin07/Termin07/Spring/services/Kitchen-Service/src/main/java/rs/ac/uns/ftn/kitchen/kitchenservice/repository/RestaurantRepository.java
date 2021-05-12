package rs.ac.uns.ftn.kitchen.kitchenservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.kitchen.kitchenservice.domain.Restaurant;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    boolean existsById(Long restaurantId);

}
